package com.tomato.utils.lang;

import com.tomato.utils.RandomUtil;
import com.tomato.utils.date.SystemClock;

import java.util.Random;

/**
 * Twitter的Snowflake 算法<br>
 * 来源：<a href="https://github.com/imadcn/idworker">idworker</a>
 * Snowflake的结构如下(每部分用-分开): <br>
 * 0 - 0000000000 0000000000 0000000000 0000000000 0 - 00000 - 00000 - 000000000000 <br>
 * <b> · </b>1位标识，由于long基本类型在Java中是带符号的，最高位是符号位，正数是0，负数是1，所以id一般是正数，最高位是0 <br>
 * <b> · </b>41位时间戳(毫秒级)，注意，41位时间戳不是存储当前时间的时间戳，而是存储时间戳的差值（当前时间戳 -
 * 开始时间戳)得到的值），这里的的开始时间戳，一般是我们的id生成器开始使用的时间，由我们程序来指定的（如下下面程序epoch属性）。41位的时间戳，可以使用69年 <br>
 * <b> · </b>10位的数据机器位，可以部署在1024个节点，包括5位datacenterId和5位workerId <br>
 * <b> · </b>12位序列，毫秒内的计数，12位的计数顺序号支持每个节点每毫秒(同一机器，同一时间戳)产生4096个ID序号 加起来刚好64位，为一个Long型。
 * <p>
 * SnowFlake的优点是，整体上按照时间自增排序，并且整个分布式系统内不会产生ID碰撞(由数据中心ID和机器ID作区分)，并且效率较高，经测试，SnowFlake每秒能够产生26万ID左右。
 * <p>
 * 注意这里进行了小改动: <br>
 * <b> · </b>Snowflake是5位的datacenter加5位的机器id; 这里变成使用10位的机器id (b) <br>
 * <b> · </b>对系统时间的依赖性非常强，需关闭ntp的时间同步功能。当检测到ntp时间调整后，将会拒绝分配id
 *
 * @author lizhifu
 * @date 2022/7/5
 */
public class Snowflake {
    /**
     * 是否使用{@link SystemClock} 获取当前时间戳
     */
    private final boolean useSystemClock = false;
    /**
     * sequence随机种子（兼容低并发下，sequence均为0的情况）
     * 当在低频模式下时，序号始终为0，导致生成ID始终为偶数<br>
     * 此属性用于限定一个随机上限，在不同毫秒下生成序号时，给定一个随机数，避免偶数问题。<br>
     * 注意次数必须小于{@link #sequenceMask}，{@code 0}表示不使用随机数。<br>
     * 这个上限不包括值本身。
     */
    private final long randomSequenceLimit = 100;
    /**
     * 允许的时钟回拨毫秒数
     */
    private final long timeOffset = 5;
    /**
     * 机器ID
     */
    private final long workerId;
    /**
     * 数据标识 ID 部分
     */
    private final long datacenterId;
    /**
     * 时间起始标记点，作为基准，一般取系统的最近时间，默认2022-07-05（一旦确定不能变动）
     */
    private final long epoch = 1656950400000L;
    /**
     * 机器id所占的位数（5位）
     */
    private final long workerIdBits = 5L;
    /**
     * 最大支持机器节点数0~31，一共32个节点
     */
    private final long datacenterIdBits = 5L;
    /**
     * 最大支持数据中心节点数0~31，一共32个
     */
    private final long maxWorkerId = -1L ^ -1L << workerIdBits;
    private final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
    /**
     * 序列在id中占的位数
     */
    private final long sequenceBits = 12L;
    /**
     * 生成序列的掩码，这里为4095 (0b111111111111=0xfff=4095)，12位
     */
    private final long sequenceMask = -1L ^ -1L << sequenceBits;
    /**
     * 机器ID向左移12位
     */
    private final long workerIdShift = sequenceBits;
    /**
     * 机器ID向左移 24位
     */
    private final long datacenterIdShift = sequenceBits + workerIdBits;
    /**
     * 时间戳向左移22位(5+5+12)
     */
    private final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
    /**
     * 并发控制，毫秒内序列(0~4095)
     */
    private long sequence = 0L;
    /**
     * 上次生成ID的时间戳
     */
    private long lastTimestamp = -1L;
    /**
     * 100,000 批量获取
     */
    private final int HUNDRED_K = 100_000;
    /**
     * 实例化一个Snowflake ID生成器
     * @param workerId 机器Id
     * @param datacenterId 数据中心Id
     */
    private Snowflake(long workerId,long datacenterId) {
        Assert.isFalse(workerId > maxWorkerId || workerId < 0, ()->{
            throw  new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        });
        Assert.isFalse(datacenterId > maxDatacenterId || datacenterId < 0, ()->{
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
        });
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }
    /**
     * Snowflake Builder
     *
     * @param workerId 机器Id
     * @return Snowflake Instance
     */
    public static Snowflake create(long workerId,long datacenterId) {
        return new Snowflake(workerId,datacenterId);
    }
    /**
     * 批量获取ID
     *
     * @param size 获取大小，最多10万个
     * @return SnowflakeId
     */
    public long[] nextId(int size) {
        if (size <= 0 || size > HUNDRED_K) {
            String message = String.format("Size can't be greater than %d or less than 0", HUNDRED_K);
            throw new IllegalArgumentException(message);
        }
        long[] ids = new long[size];
        for (int i = 0; i < size; i++) {
            ids[i] = nextId();
        }
        return ids;
    }
    /**
     * 获得ID
     *
     * @return SnowflakeId
     */
    public synchronized long nextId() {
        long timestamp = timeGen();
        // 如果上一个timestamp与新产生的相等，则sequence加一(0-4095循环);
        if (lastTimestamp == timestamp) {
            // 对新的timestamp，sequence从0开始
            sequence = sequence + 1 & sequenceMask;
            // 毫秒内序列溢出
            if (sequence == 0) {
                // 阻塞到下一个毫秒,获得新的时间戳
                sequence = RandomUtil.randomLong(randomSequenceLimit);
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            // 时间戳改变，毫秒内序列重置
            sequence = RandomUtil.randomLong(randomSequenceLimit);
        }

        // 如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
        if (timestamp < lastTimestamp) {
            if (this.lastTimestamp - timestamp < timeOffset) {
                // 容忍指定的回拨，避免NTP校时造成的异常
                timestamp = lastTimestamp;
            } else {
                // 如果服务器时间有问题(时钟后退) 报错。
                String message = String.format("Clock moved backwards. Refusing to generate id for %d milliseconds.",(lastTimestamp - timestamp));
                throw new IllegalStateException(message);
            }
        }
        lastTimestamp = timestamp;
        // 移位并通过或运算拼到一起组成64位的ID
        // 时间戳部分 | 数据中心部分 | 机器标识部分 | 序列号部分
        return ((timestamp - epoch) << timestampLeftShift)
                | (datacenterId << datacenterIdShift)
                | (workerId << workerIdShift)
                | sequence;
    }
    /**
     * 下一个ID（字符串形式）
     *
     * @return ID 字符串形式
     */
    public String nextIdStr() {
        return Long.toString(nextId());
    }
    /**
     * 等待下一个毫秒的到来, 保证返回的毫秒数在参数lastTimestamp之后
     *
     * @param lastTimestamp 上次生成ID的时间戳
     * @return 下一个毫秒
     */
    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        // 循环直到操作系统时间戳变化
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }
    /**
     * 获得系统当前毫秒数
     *
     * @return 获得系统当前毫秒数
     */
    private long timeGen() {
        return this.useSystemClock ? SystemClock.now() : System.currentTimeMillis();
    }
}
