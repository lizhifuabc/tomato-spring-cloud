package com.tomato.notify.listener;

import com.rabbitmq.client.Channel;
import com.tomato.notify.component.NotifyRecordComponent;
import com.tomato.notify.config.ThreadPoolTaskConfig;
import com.tomato.notify.database.dataobject.NotifyRecordDO;
import com.tomato.notify.exception.NotifyException;
import com.tomato.notify.dto.NoticeReceiveReq;
import com.tomato.notify.service.NotifyRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * 账户通知
 *
 * @author lizhifu
 * @date 2022/6/20
 */
@Slf4j
@Component
public class NotifyListener {
    private final NotifyRecordComponent notifyRecordComponent;

    public NotifyListener(NotifyRecordComponent notifyRecordComponent) {
        this.notifyRecordComponent = notifyRecordComponent;
    }

    @Async(ThreadPoolTaskConfig.TASK_EXECUTOR_POOL)
    @RabbitListener(queues = "order.callback.notice.queue",ackMode = "MANUAL")
    public void account(NoticeReceiveReq noticeReceiveReq, Message message, Channel channel, @Headers Map<String, Object> headers) throws IOException {
        log.info("支付回调：商户通知 {}",noticeReceiveReq);
        try {
            NotifyRecordDO notifyRecordDO = notifyRecordComponent.create(noticeReceiveReq);
            notifyRecordComponent.sendNotify(notifyRecordDO.getNotifyId());
            // deliveryTag（唯一标识 ID）
            // multiple：为了减少网络流量，手动确认可以被批处理，当该参数为 true 时，则可以一次性确认 delivery_tag 小于等于传入值的所有消息
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }catch (NotifyException notifyException){
            log.error("支付回调：通知异常",notifyException);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }catch (Exception e) {
            log.error("message consume failed: " + e.getMessage());
            // ack返回false，requeue-true并重新回到队列
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
        }
    }
}
