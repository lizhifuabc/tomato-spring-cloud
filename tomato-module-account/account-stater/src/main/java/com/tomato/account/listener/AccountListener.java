package com.tomato.account.listener;

import com.rabbitmq.client.Channel;
import com.tomato.account.service.AccountCheckService;
import com.tomato.account.component.AccountComponent;
import com.tomato.account.database.dataobject.AccountDO;
import com.tomato.account.dto.AccountReceiveReq;
import com.tomato.account.exception.AccountException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * 账号入账监听器
 *
 * @author lizhifu
 * @date 2022/6/20
 */
@Slf4j
@Component
public class AccountListener {
    private final AccountComponent accountComponent;

    private final AccountCheckService accountCheckService;

    public AccountListener(AccountComponent accountComponent, AccountCheckService accountCheckService) {
        this.accountComponent = accountComponent;
        this.accountCheckService = accountCheckService;
    }

    @RabbitListener(queues = "order.callback.account.queue",ackMode = "MANUAL")
    public void account(AccountReceiveReq accountReceiveReq, Message message, Channel channel,@Headers Map<String, Object> headers) throws IOException {
        log.info("支付回调：账号入账 {}",accountReceiveReq);
        try {
            AccountDO accountDO = accountCheckService.checkReceive(accountReceiveReq);
            accountComponent.receive(accountReceiveReq,accountDO);
            // deliveryTag（唯一标识 ID）
            // multiple：为了减少网络流量，手动确认可以被批处理，当该参数为 true 时，则可以一次性确认 delivery_tag 小于等于传入值的所有消息
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }catch (AccountException accountException){
            log.error("支付回调：账号入账异常",accountException);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }catch (Exception e) {
            log.error("message consume failed: " + e.getMessage());
            // ack返回false，requeue-true并重新回到队列
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
        }
    }
}
