package com.momo.springbootrabbitmq.single.queue;

import com.momo.springbootrabbitmq.single.po.User;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @author: MQG
 * @date: 2018/11/6
 */
@Service
public class Consumer {
    
    @RabbitListener(queues = {"${rabbitmq.queue.msg}"})
    public void receiveMsg(String msg) {
        System.out.println("c1 - 收到消息：" + msg);
    }

    @RabbitListener(queues = {"${rabbitmq.queue.msg}"})
    public void receiveMsg2(String msg) {
        System.out.println("\t\tc2 - 收到消息：" + msg);
    }
    
    @RabbitListener(queues = {"${rabbitmq.queue.user}"})
    public void receiveUser(User user) {
        System.out.println("收到用户消息：" + user);
    }
    
}
