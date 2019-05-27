package com.momo.springbootrabbitmq.topic.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author: MQG
 * @date: 2018/11/6
 */
@Service
public class TopicConsumer {

    @RabbitListener(queues = "${rabbitmq.topic.send.queue}")
    public void receiveMsg(String msg) {
        System.out.println("c1 - " + msg);
    }

    @RabbitListener(queues = "${rabbitmq.topic.messages.queue}")
    public void receiveMsgs(String msg) {
        System.out.println("c2 - " + msg);
    }
    
}
