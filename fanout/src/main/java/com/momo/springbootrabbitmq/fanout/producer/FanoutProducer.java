package com.momo.springbootrabbitmq.fanout.producer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 扇形交换器
 *
 * @author: MQG
 * @date: 2018/11/6
 */
@Service
public class FanoutProducer {

    @Value("${rabbitmq.fanout.exchangeName}")
    private String exchangeName;

    @Autowired
    private AmqpTemplate rabbitTemplate;

    /**
     * 扇形交换器不匹配routingKey
     * */
    public void send() {
        String msgString = "FanoutSender: fanout send";
        System.out.println(msgString);
        this.rabbitTemplate.convertAndSend(exchangeName, "", msgString);
    }
}
