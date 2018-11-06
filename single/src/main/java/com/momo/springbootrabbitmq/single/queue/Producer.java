package com.momo.springbootrabbitmq.single.queue;

import com.momo.springbootrabbitmq.single.po.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author: MQG
 * @date: 2018/11/6
 */
@Service
public class Producer {

    @Value("${rabbitmq.queue.msg}")
    private String msgQueueName;

    @Value("${rabbitmq.queue.user}")
    private String userQueueName;

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void sendMsg1(String msg) {
        rabbitTemplate.convertAndSend(msgQueueName, "p1 - " + msg);
    }

    public void sendMsg2(String msg) {
        rabbitTemplate.convertAndSend(msgQueueName, "p2 - " + msg);
    }

    public void sendUser(User user) {
        rabbitTemplate.convertAndSend(userQueueName, user);
    }

}
