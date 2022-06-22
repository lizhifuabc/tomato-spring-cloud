package com.tomato.order.controller;

import com.tomato.order.dto.AccountReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * 支付回调控制器
 *
 * @author lizhifu
 * @date 2022/6/20
 */
@RestController
@RequestMapping("/callback")
@Slf4j
public class CallBackController {
    private final RabbitTemplate rabbitTemplate;

    public CallBackController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @RequestMapping("/wx")
    public void wx(String orderNo) {
        log.info("支付回调：微信支付 {}",orderNo);
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        AccountReq accountReq = AccountReq.builder()
                .accountId(System.currentTimeMillis())
                .thirdNo(orderNo)
                .amount(new BigDecimal(100))
                .accountHisType("支付")
                .build();
        rabbitTemplate.convertAndSend("order.callback.exchange", null, accountReq,correlationData);
    }
}
