package com.momo.springbootrabbitmq.topic.producer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author: MQG
 * @date: 2018/11/6
 */
@Service
public class TopicProducer {

    @Value("${rabbitmq.topic.message.queue}")
    private String topicMessageQueue;

    @Value("${rabbitmq.topic.messages.queue}")
    private String topicMessagesQueue;

    @Value("${rabbitmq.topic.exchange}")
    private String topicExchange;

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        // xx.topic.message、xx.topic.messages队列都可以收到消息
        String msg1 = topicMessageQueue + " msg";
        this.rabbitTemplate.convertAndSend(topicExchange, topicMessageQueue, msg1);

        // xx.topic.messages队列可以收到消息
        String msg2 = topicMessagesQueue + " msg";
        this.rabbitTemplate.convertAndSend(topicExchange, topicMessagesQueue, msg2);
    }

}
