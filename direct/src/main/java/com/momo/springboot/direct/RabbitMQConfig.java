package com.momo.springboot.direct;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.direct.queue.a}")
    private String aQueueName;

    @Value("${rabbitmq.direct.queue.b}")
    private String bQueueName;

    @Value("${rabbitmq.direct.routingKey}")
    private String routingKey;

    @Value("${rabbitmq.direct.exchangeName}")
    private String exchangeName;

    @Bean
    public Queue aQueue() {
        return new Queue(aQueueName);
    }

    @Bean
    public Queue bQueue() {
        return new Queue(bQueueName);
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(exchangeName);
    }

    @Bean
    Binding bindingQueueA(Queue aQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(aQueue).to(directExchange).with(routingKey);
    }

    @Bean
    Binding bindingQueueB(Queue bQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(bQueue).to(directExchange).with(routingKey);
    }
}
