package com.tomato.utils.lang;

import com.tomato.utils.date.SystemClock;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 分布式高效有序 ID 生产黑科技(sequence)
 *
 * <p>优化开源项目：<a href="https://gitee.com/yu120/sequence">sequence</a></p>
 *
 * @author hubin
 * @since 2016-08-18
 */
public class Sequence {
    /**
     * 是否使用{@link SystemClock} 获取当前时间戳
     */
    private final boolean useSystemClock = false;
    /**
     * 允许的时钟回拨毫秒数,2S
     * 容忍指定的回拨，避免NTP校时造成的异常
     */
    private final long timeOffset = 2000L;
    /**
     * 时间起始标记点，作为基准，一般取系统的最近时间，默认2022-07-05（一旦确定不能变动）
     */
    private final long epoch = 1656950400000L;
    /**
     * 机器标识位数
     */
    private final long workerIdBits = 5L;
    private final long datacenterIdBits = 5L;
    /**
     * 机器ID最大值: 31 (从0开始)
     */
    private final long maxWorkerId = -1L ^ (-1L << workerIdBits);
    private final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
    /**
     * 毫秒内自增位
     */
    private final long sequenceBits = 12L;
    private final long workerIdShift = sequenceBits;
    private final long datacenterIdShift = sequenceBits + workerIdBits;
    /**
     * 时间戳左移动位
     */
    private final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
    private final long sequenceMask = -1L ^ (-1L << sequenceBits);

    private final long workerId;

    /**
     * 数据标识 ID 部分
     */
    private final long datacenterId;
    /**
     * 并发控制
     */
    private long sequence = 0L;
    /**
     * 上次生产 ID 时间戳
     */
    private long lastTimestamp = -1L;
    /**
     * IP 地址
     */
    private InetAddress inetAddress;

    public Sequence(InetAddress inetAddress) {
        this.inetAddress = inetAddress;
        this.datacenterId = getDatacenterId(maxDatacenterId);
        this.workerId = getWorkerId(datacenterId, maxWorkerId);
    }

    /**
     * 有参构造器
     *
     * @param workerId     工作机器 ID
     * @param datacenterId 序列号
     */
    public Sequence(long workerId, long datacenterId) {
        Assert.isFalse(workerId > maxWorkerId || workerId < 0, ()->{
            return new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        });
        Assert.isFalse(datacenterId > maxDatacenterId || datacenterId < 0, ()->{
            return new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
        });
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }

    /**
     * 获取机器ID，使用进程ID配合数据中心ID生成<br>
     * 机器依赖于本进程ID或进程名的Hash值。
     */
    protected long getWorkerId(long datacenterId, long maxWorkerId) {
        StringBuilder mpid = new StringBuilder();
        mpid.append(datacenterId);
        try {
            mpid.append(Pid.INSTANCE.get());
        } catch (RuntimeException runtimeException) {
            //ignore
        }
        /*
         * MAC + PID 的 hashcode 获取16个低位
         */
        return (mpid.toString().hashCode() & 0xffff) % (maxWorkerId + 1);
    }

    /**
     * 获取数据中心ID<br>
     * 数据中心ID依赖于本地网卡MAC地址。
     * @param maxDatacenterId 最大数据中心ID
     */
    protected long getDatacenterId(long maxDatacenterId) {
        long id = 0L;
        try {
            if (null == this.inetAddress) {
                this.inetAddress = InetAddress.getLocalHost();
            }
            NetworkInterface network = NetworkInterface.getByInetAddress(this.inetAddress);
            if (null == network) {
                id = 1L;
            } else {
                byte[] mac = network.getHardwareAddress();
                if (null != mac) {
                    id = ((0x000000FF & (long) mac[mac.length - 2]) | (0x0000FF00 & (((long) mac[mac.length - 1]) << 8))) >> 6;
                    id = id % (maxDatacenterId + 1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    /**
     * 获取下一个 ID
     *
     * @return 下一个 ID
     */
    public synchronized long nextId() {
        long timestamp = timeGen();
        //闰秒
        if (timestamp < lastTimestamp) {
            // 容忍指定的回拨，避免NTP校时造成的异常
            long offset = lastTimestamp - timestamp;
            if (offset <= 5) {
                try {
                    wait(offset << 1);
                    timestamp = timeGen();
                    if (timestamp < lastTimestamp) {
                        throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", offset));
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } else {
                throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", offset));
            }
        }

        if (lastTimestamp == timestamp) {
            // 相同毫秒内，序列号自增
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                // 同一毫秒的序列数已经达到最大
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            // 不同毫秒内，序列号置为 1 - 3 随机数
            sequence = ThreadLocalRandom.current().nextLong(1, 3);
        }

        lastTimestamp = timestamp;

        // 时间戳部分 | 数据中心部分 | 机器标识部分 | 序列号部分
        return ((timestamp - epoch) << timestampLeftShift)
            | (datacenterId << datacenterIdShift)
            | (workerId << workerIdShift)
            | sequence;
    }

    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 生成时间戳
     * @return
     */
    protected long timeGen() {
        return this.useSystemClock ? SystemClock.now() : System.currentTimeMillis();
    }

}
