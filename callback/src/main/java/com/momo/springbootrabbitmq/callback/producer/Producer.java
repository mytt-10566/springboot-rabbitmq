package com.momo.springbootrabbitmq.callback.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author: MQG
 * @date: 2018/11/6
 */
@Service
public class Producer {

    @Value("${rabbitmq.queue.callback}")
    private String callbackQueue;
    
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private ProducerCallback producerCallback;
    
    public void send() {
        rabbitTemplate.setConfirmCallback(producerCallback);

        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        System.out.println("UUID - " + correlationData.getId());
        rabbitTemplate.convertAndSend("", callbackQueue, "callback msg", correlationData);
    }
}
