package com.tomato.rabbitmq.init;

import com.tomato.rabbitmq.config.RabbitInfo;
import com.tomato.rabbitmq.config.RabbitProperties;
import com.tomato.rabbitmq.enums.RabbitExchangeTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * RabbitMQInitializer
 *
 * @author lizhifu
 * @date 2022/6/19
 */
@Slf4j
public class RabbitInitializer implements SmartInitializingSingleton {
    private final AmqpAdmin amqpAdmin;
    private final RabbitProperties rabbitProperties;
    public RabbitInitializer(AmqpAdmin amqpAdmin, RabbitProperties rabbitProperties) {
        this.amqpAdmin = amqpAdmin;
        this.rabbitProperties = rabbitProperties;
    }

    @Override
    public void afterSingletonsInstantiated() {
        log.info("RabbitMQ 根据配置动态创建和绑定队列、交换机");
        List<RabbitInfo> rabbitInfoList = rabbitProperties.getRabbitInfoList();
        if (ObjectUtils.isEmpty(rabbitInfoList)) {
            return;
        }
        rabbitInfoList.forEach(rabbitInfo -> {
            check(rabbitInfo);
            // 队列
            Queue queue = convertQueue(rabbitInfo.getQueue());
            // 交换机
            Exchange exchange = convertExchange(rabbitInfo.getExchange());

            // 绑定关系
            String routingKey = rabbitInfo.getRoutingKey() == null ? "" : rabbitInfo.getRoutingKey();
            String queueName = rabbitInfo.getQueue().getName();
            String exchangeName = rabbitInfo.getExchange().getName();
            Binding binding = new Binding(queueName, Binding.DestinationType.QUEUE, exchangeName, routingKey, null);

            // 创建队列
            amqpAdmin.declareQueue(queue);
            amqpAdmin.declareExchange(exchange);
            amqpAdmin.declareBinding(binding);
        });
    }
    /**
     * RabbitMQ动态配置参数校验
     *
     * @param rabbitInfo
     */
    public void check(RabbitInfo rabbitInfo) {

    }
    /**
     * 转换生成RabbitMQ队列
     *
     * @param queue
     * @return
     */
    public Queue convertQueue(RabbitInfo.Queue queue) {
        Map<String, Object> arguments = queue.getArguments();
        if (ObjectUtils.isEmpty(arguments)) {
            arguments = new HashMap<>(4);
        }
        // 转换ttl的类型为long
//        if (arguments.containsKey("x-message-ttl")) {
//            arguments.put("x-message-ttl", Convert.toLong(arguments.get("x-message-ttl")));
//        }
        // 死信队列
        String deadLetterExchange = queue.getDeadLetterExchange();
        String deadLetterRoutingKey = queue.getDeadLetterRoutingKey();
        if (!ObjectUtils.isEmpty(deadLetterExchange) && !ObjectUtils.isEmpty(deadLetterRoutingKey)){
            arguments.put("x-dead-letter-exchange", deadLetterExchange);
            arguments.put("x-dead-letter-routing-key", deadLetterRoutingKey);
        }
        return new Queue(queue.getName(), queue.isDurable(), queue.isExclusive(), queue.isAutoDelete(), arguments);
    }
    /**
     * 转换生成RabbitMQ交换机
     *
     * @param exchangeInfo
     * @return
     */
    public Exchange convertExchange(RabbitInfo.Exchange exchangeInfo) {

        AbstractExchange exchange = null;

        RabbitExchangeTypeEnum exchangeType = exchangeInfo.getType();

        String exchangeName = exchangeInfo.getName();
        boolean isDurable = exchangeInfo.isDurable();
        boolean isAutoDelete = exchangeInfo.isAutoDelete();

        Map<String, Object> arguments = exchangeInfo.getArguments();

        switch (exchangeType) {
            // 直连交换机
            case DIRECT:
                exchange = new DirectExchange(exchangeName, isDurable, isAutoDelete, arguments);
                break;
            // 主题交换机
            case TOPIC:
                exchange = new TopicExchange(exchangeName, isDurable, isAutoDelete, arguments);
                break;
            //扇形交换机
            case FANOUT:
                exchange = new FanoutExchange(exchangeName, isDurable, isAutoDelete, arguments);
                break;
            // 头交换机
            case HEADERS:
                exchange = new HeadersExchange(exchangeName, isDurable, isAutoDelete, arguments);
                break;
        }
        return exchange;
    }
}
