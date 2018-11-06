package com.momo.springbootrabbitmq.callback.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @author: MQG
 * @date: 2018/11/6
 */
@Service
public class Consumer {

    @RabbitListener(queues = "${rabbitmq.queue.callback}")
    public void receiveMsg(String msg) {
        System.out.println("callback 接收到消息：" + msg);
    }
}
