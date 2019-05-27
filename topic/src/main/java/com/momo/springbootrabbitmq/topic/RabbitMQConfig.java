package com.momo.springbootrabbitmq.topic;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: MQG
 * @date: 2018/11/6
 */
@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.topic.send.queue}")
    private String topicMessageQueue;

    @Value("${rabbitmq.topic.send.routingKey}")
    private String messageRoutingKey;

    @Value("${rabbitmq.topic.messages.queue}")
    private String topicMessagesQueue;

    @Value("${rabbitmq.topic.messages.routingKey}")
    private String messagesRoutingKey;

    @Value("${rabbitmq.topic.exchangeName}")
    private String exchangeName;

    @Bean
    public Queue queueMessage() {
        return new Queue(topicMessageQueue);
    }

    @Bean
    public Queue queueMessages() {
        return new Queue(topicMessagesQueue);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(exchangeName);
    }

    /**
     * 将队列topic.message与exchange绑定，binding_key为topic.send,就是完全匹配
     *
     * @param queueMessage
     * @param exchange
     * @return
     */
    @Bean
    Binding bindingExchangeMessage(Queue queueMessage, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange).with(messageRoutingKey);
    }

    /**
     * 将队列topic.messages与exchange绑定，binding_key为topic.#,模糊匹配（*一个单词 #任意多个单词）
     *
     * @param queueMessages
     * @param exchange
     * @return
     */
    @Bean
    Binding bindingExchangeMessages(Queue queueMessages, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessages).to(exchange).with(messagesRoutingKey);
    }
}
