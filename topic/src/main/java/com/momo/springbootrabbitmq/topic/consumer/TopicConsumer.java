package com.momo.springbootrabbitmq.topic.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @author: MQG
 * @date: 2018/11/6
 */
@Service
public class TopicConsumer {
    
    @RabbitListener(queues = "topic.message")
    public void receiveMsg(String msg) {
        System.out.println("c1 - 接收到消息：" + msg);
    }

    @RabbitListener(queues = "topic.messages")
    public void receiveMsgs(String msg) {
        System.out.println("c2 - 接收到消息：" + msg);
    }
    
}
