package com.momo.springbootrabbitmq.fanout;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: MQG
 * @date: 2018/11/6
 */
@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue aQueue() {
        return new Queue("fanout.a");
    }

    @Bean
    public Queue bQueue() {
        return new Queue("fanout.b");
    }

    @Bean
    public Queue cQueue() {
        return new Queue("fanout.c");
    }

    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");
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
