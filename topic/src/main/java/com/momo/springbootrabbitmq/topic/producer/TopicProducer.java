package com.momo.springbootrabbitmq.topic.producer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: MQG
 * @date: 2018/11/6
 */
@Service
public class TopicProducer {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        // topic.message、topic.messages队列都可以收到消息
        String msg1 = "topic.message msg";
        this.rabbitTemplate.convertAndSend("exchange", "topic.message", msg1);

        // topic.messages队列可以收到消息
        String msg2 = "topic.messages msg";
        this.rabbitTemplate.convertAndSend("exchange", "topic.messages", msg2);
    }

}
