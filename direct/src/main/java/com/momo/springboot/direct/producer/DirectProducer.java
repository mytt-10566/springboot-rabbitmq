package com.momo.springboot.direct.producer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Direct交换器
 *
 * @author: MQG
 * @date: 2018/11/6
 */
@Service
public class DirectProducer {

    @Value("${rabbitmq.direct.exchangeName}")
    private String exchangeName;
    @Value("${rabbitmq.direct.routingKey}")
    private String routingKey;

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String msgString = "DirectSender: direct message";
        this.rabbitTemplate.convertAndSend(exchangeName, routingKey, msgString);
    }
}
