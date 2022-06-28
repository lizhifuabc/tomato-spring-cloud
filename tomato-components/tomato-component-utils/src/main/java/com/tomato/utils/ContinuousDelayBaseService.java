package com.tomato.utils;

import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.function.Function;
/**
 * @author lizhifu
 * @param <T> 延迟业务数据的类型
 */
public abstract class ContinuousDelayBaseService<T> {

    private static final DelayQueue<DelayBean> queue = new DelayQueue<>();

    private static ExecutorService asyncDoneExecutor = Executors.newCachedThreadPool();

    private static final Thread thread = new Thread(() -> {
        Thread.currentThread().setName("ContinuousDelay-main");
        while (true) {
            try {
                DelayBean delayBean = queue.take();
                asyncDoneExecutor.submit(() -> {
                    Thread.currentThread().setName("ContinuousDelay-" + delayBean.id);
                    delayBean.done.accept(delayBean.data);
                });

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });

    static {
        thread.start();
    }

    /**
     * 最后一次超时回调
     * @param value
     */
    protected abstract void done(T value);

    /**
     * @param delayMs 延迟毫秒数
     * @param callback4InitOrIfExists 回调函数：初次调用时，参数为空；重复调用时，参数为上次调用的返回值
     */
    public synchronized void delay(Long delayMs, Function<T, T> callback4InitOrIfExists) {
        DelayBean delayBean = this.getDelayBean();
        if (delayBean == null) {
            T newValue = callback4InitOrIfExists != null ? callback4InitOrIfExists.apply(null) : null;
            delayBean = new DelayBean(this.getClass().getName(), delayMs, newValue);
            delayBean.done = (value) -> this.done((T) value);
        } else {
            deleteById(this.getClass().getName());
            if (callback4InitOrIfExists != null) {
                Object newValue = callback4InitOrIfExists.apply((T) delayBean.data);
                delayBean.data = newValue;
            }
            delayBean.setDelay(delayMs);
        }
        queue.offer(delayBean);
    }

    /**
     *获取延时队列数据
     */
    private DelayBean getDelayBean() {
        Iterator<DelayBean> iterator = queue.iterator();
        while (iterator.hasNext()) {
            DelayBean next = iterator.next();
            if (Objects.equals(next.id, this.getClass().getName())) {
                return next;
            }
        }
        return null;
    }

    private boolean deleteById(String id) {
        return queue.remove(new DelayBean(id));
    }


    protected static class DelayBean implements Delayed {

        private String id;

        long delay; //延迟时间
        long expire;  //到期时间
        Object data;   //数据

        Consumer<Object> done;

        protected void setDelay(long delay) {
            expire = System.currentTimeMillis() + delay;
        }

        public DelayBean(String id) {
            this.id = id;
        }

        public DelayBean(String id, long delay, Object data){
            this.id = id;
            this.delay = delay;
            setDelay(delay);    //到期时间 = 当前时间+延迟时间
            this.data = data;
        }


        /**
         * 需要实现的接口，获得延迟时间   用过期时间-当前时间
         * @param unit
         * @return
         */
        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(this.expire - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        /**
         * 用于延迟队列内部比较排序   当前时间的延迟时间 - 比较对象的延迟时间
         * @param o
         * @return
         */
        @Override
        public int compareTo(Delayed o) {
            return (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
        }

        @Override
        public int hashCode() {
            return this.getClass().hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj  == null)
                return false;
            if (!(obj instanceof DelayBean)) {
                return false;
            }
            return Objects.equals(this.id, ((DelayBean)obj).id);
        }
    }
}
