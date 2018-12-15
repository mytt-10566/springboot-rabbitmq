package com.momo.springboot.direct.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @author: MQG
 * @date: 2018/11/6
 */
@Service
public class DirectConsumer {
    
    @RabbitListener(queues = "${rabbitmq.direct.queue.a}")
    public void receiveDirectQueueA(String msg) {
        System.out.println("a - " + msg);
    }

    @RabbitListener(queues = "${rabbitmq.direct.queue.b}")
    public void receiveDirectQueueB(String msg) {
        System.out.println("b - " + msg);
    }
}
