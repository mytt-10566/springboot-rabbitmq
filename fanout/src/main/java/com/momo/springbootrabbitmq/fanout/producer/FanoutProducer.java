package com.momo.springbootrabbitmq.fanout.producer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: MQG
 * @date: 2018/11/6
 */
@Service
public class FanoutProducer {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String msgString = "fanoutSender :hello i am fanout message";
        System.out.println(msgString);
        this.rabbitTemplate.convertAndSend("fanoutExchange", "abcd.ee", msgString);
    }
}
