package com.tomato.account.listener;

import com.rabbitmq.client.Channel;
import com.tomato.account.component.AccountComponent;
import com.tomato.account.dto.AccountReceiveReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

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

    public AccountListener(AccountComponent accountComponent) {
        this.accountComponent = accountComponent;
    }

    @RabbitListener(queues = "order.callback.account.queue",ackMode = "MANUAL")
    public void account(String accountReceiveReq, Message message, Channel channel) {
        log.info("支付回调：账号入账 {}",accountReceiveReq);
//        accountComponent.receive(accountReceiveReq);
    }
}
