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

    @Value("${rabbitmq.topic.message.routingKey}")
    private String messageRoutingKey;

    @Value("${rabbitmq.topic.messages.routingKey}")
    private String messagesRoutingKey;

    @Value("${rabbitmq.topic.exchangeName}")
    private String exchangeName;

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        // topic.queue.message、topic.queue.messages队列都可以收到消息
        String msg1 = "msg 1";
        this.rabbitTemplate.convertAndSend(exchangeName, messageRoutingKey, msg1);

        // 仅topic.queue.messages队列可以收到消息（topic.messages只与topic.#绑定键匹配）
        String msg2 = "msg 2";
        this.rabbitTemplate.convertAndSend(exchangeName, "topic.messages", msg2);
    }

}
