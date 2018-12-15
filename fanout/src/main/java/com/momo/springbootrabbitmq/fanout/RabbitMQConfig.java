package com.momo.springbootrabbitmq.fanout;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
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

    @Value("${rabbitmq.fanout.queue.a}")
    private String aQueueName;
    @Value("${rabbitmq.fanout.queue.b}")
    private String bQueueName;
    @Value("${rabbitmq.fanout.queue.c}")
    private String cQueueName;

    @Value("${rabbitmq.fanout.exchangeName}")
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
    public Queue cQueue() {
        return new Queue(cQueueName);
    }

    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange(exchangeName);
    }

    @Bean
    Binding bindingExchangeA(Queue aQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(aQueue).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeB(Queue bQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(bQueue).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeC(Queue cQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(cQueue).to(fanoutExchange);
    }
}
