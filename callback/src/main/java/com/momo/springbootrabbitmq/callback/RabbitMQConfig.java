package com.momo.springbootrabbitmq.callback;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: MQG
 * @date: 2018/11/6
 */
@Configuration
public class RabbitMQConfig {
    
    @Value("${rabbitmq.queue.callback}")
    private String callbackQueue;
    
    @Bean
    public Queue callbackQueue() {
        return new Queue(callbackQueue);
    }
}
