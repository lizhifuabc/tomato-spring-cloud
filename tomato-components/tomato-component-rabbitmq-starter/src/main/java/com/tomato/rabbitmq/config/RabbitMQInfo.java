package com.tomato.rabbitmq.config;

import com.tomato.rabbitmq.enums.RabbitMQExchangeTypeEnum;
import lombok.Data;

import java.util.Map;

/**
 * rabbitmq配置信息
 *
 * @author lizhifu
 * @date 2022/6/19
 */
@Data
public class RabbitMQInfo {
    /**
     * 路由Key
     */
    private String routingKey = "";

    /**
     * 队列信息
     */
    private Queue queue;

    /**
     * 交换机信息
     */
    private Exchange exchange;

    @Data
    public static class Exchange {

        /**
         * 交换机类型
         * 默认直连交换机
         */
        private RabbitMQExchangeTypeEnum type = RabbitMQExchangeTypeEnum.DIRECT;

        /**
         * 交换机名称
         */
        private String name;

        /**
         * 是否持久化
         * 默认true持久化，重启消息不会丢失
         */
        private boolean durable = true;

        /**
         * 当所有队绑定列均不在使用时，是否自动删除交换机
         * 默认false，不自动删除
         */
        private boolean autoDelete = false;

        /**
         * 交换机其他参数
         */
        private Map<String, Object> arguments;
    }

    /**
     * 队列信息类
     */
    @Data
    public static class Queue {

        /**
         * 队列名称
         */
        private String name;

        /**
         * 是否持久化
         * 默认true持久化，重启消息不会丢失
         * 队列的持久化只能保证其队列本身的元数据不会被丢失，
         * 但是不能保证消息不会被丢失。所以消息本身也需要被持久化，
         * 可以在投递消息前设置AMQP.BasicProperties的属性deliveryMode为2
         */
        private boolean durable = true;

        /**
         * 是否具有排他性
         * 默认false，可多个消费者消费同一个队列
         */
        private boolean exclusive = false;

        /**
         * 当消费者均断开连接，是否自动删除队列
         * 默认false,不自动删除，避免消费者断开队列丢弃消息
         */
        private boolean autoDelete = false;

        /**
         * 绑定死信队列的交换机名称
         * 生产者 --> 发送消息 --> 业务交换机 --> 队列 --> 成为死信消息 --> 死信交换机 --> 死信队列 --> 监听死信队列的消费者
         * x-message-ttl时间到期后把消息放到
         * x-dead-letter-routing-key和x-dead-letter-exchange指定的队列中达到延迟队列的目的。
         */
        private String deadLetterExchange;

        /**
         * 绑定死信队列的路由key
         */
        private String deadLetterRoutingKey;

        /**
         * 1. x-message-ttl 消息存活时间,该参数是非负整数值.创建queue时设置该参数可指定消息在该queue中待多久，
         *                  可根据x-dead-letter-routing-key和x-dead-letter-exchange生成可延迟的死信队列。
         * 2. x-expires     存活时间,创建queue时参数arguments设置了x-expires参数，该queue会在x-expires到期后queue消息，
         *                  直接消失（哪怕里面有未消费的消息）。
         * 3. x-max-length  消息条数限制,该参数是非负整数值。限制加入queue中消息的条数。先进先出原则，
         *                  超过10条后面的消息会顶替前面的消息。
         * 4. x-max-length-bytes    消息容量限制,该参数是非负整数值。该参数和x-max-length目的一样限制队列的容量，
         *                          队列大小（bytes）来达到限制。
         * 5. x-dead-letter-exchange 设置队列溢出⾏为。这决定了在达到队列的最⼤长度时消息会发⽣什么。有效值为drop-head或
         * reject-publish。交换的可选名称，如果消息被拒绝或过期，将重新发布这些名称。
         * 6. x-dead-letter-routing-key 可选的替换路由密钥，⽤于在消息以字母为单位时使⽤。如果未设置，将使⽤消息的原始路由密
         * 钥。
         * 7. x-max-priority 队列⽀持的最⼤优先级数;如果未设置，队列将不⽀持消息优先级。
         * 8. x-queue-mode 将队列设置为延迟模式，在磁盘上保留尽可能多的消息以减少内存使⽤;如果未设置，队列将保留内存缓存以尽
         * 快传递消息。
         * 9.  x-queue-master-locator 将队列设置为主位置模式，确定在节点集群上声明时队列主机所在的规则。
         */
        private Map<String, Object> arguments;
    }
}
