package com.tomato.order.controller;

import com.tomato.order.component.OrderCompleteComponent;
import com.tomato.order.enums.OrderStatusEnum;
import com.tomato.order.enums.PayStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    private final OrderCompleteComponent orderCompleteComponent;
    public CallBackController(RabbitTemplate rabbitTemplate, OrderCompleteComponent orderCompleteComponent) {
        this.rabbitTemplate = rabbitTemplate;
        this.orderCompleteComponent = orderCompleteComponent;
    }

    @RequestMapping("/wx")
    public void wx(String payNo) {
        log.info("支付回调：微信支付 {}",payNo);
        orderCompleteComponent.complete(payNo, OrderStatusEnum.SUCCESS, PayStatusEnum.SUCCESS,"支付成功");
//        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
//        AccountReq accountReq = AccountReq.builder()
//                .accountId(System.currentTimeMillis())
//                .thirdNo(payNo)
//                .amount(new BigDecimal(100))
//                .accountHisType("支付")
//                .build();
//        rabbitTemplate.convertAndSend("order.callback.exchange", null, accountReq,correlationData);
    }
}
