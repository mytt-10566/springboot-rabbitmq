package com.momo.springbootrabbitmq.callback.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.stereotype.Service;

/**
 * @author: MQG
 * @date: 2018/11/6
 */
@Service
public class ProducerCallback implements RabbitTemplate.ConfirmCallback {

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        System.out.println("callback confirm: " + correlationData.getId());
        System.out.println(ack ? "成功消费" : "消费失败：" + cause);
    }
}
