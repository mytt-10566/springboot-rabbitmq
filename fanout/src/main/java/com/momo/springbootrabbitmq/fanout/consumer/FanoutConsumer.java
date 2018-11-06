package com.momo.springbootrabbitmq.fanout.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @author: MQG
 * @date: 2018/11/6
 */
@Service
public class FanoutConsumer {
    
    @RabbitListener(queues = "fanout.a")
    public void receiveFanoutA(String msg) {
        System.out.println("a - " + msg);
    }

    @RabbitListener(queues = "fanout.b")
    public void receiveFanoutB(String msg) {
        System.out.println("b - " + msg);
    }

    @RabbitListener(queues = "fanout.c")
    public void receiveFanoutC(String msg) {
        System.out.println("c - " + msg);
    }
}
