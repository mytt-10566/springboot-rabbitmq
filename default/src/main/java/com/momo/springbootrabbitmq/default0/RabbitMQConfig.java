package com.momo.springbootrabbitmq.default0;

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

    @Value("${rabbitmq.queue.msg}")
    private String msgQueueName;

    @Value("${rabbitmq.queue.user}")
    private String userQueueName;

    // 绑定到默认的交换器上（名称为空的Direct交换器），绑定键和队列名称相同
    @Bean
    public Queue createQueueMsg() {
        // true表示持久化消息
        return new Queue(msgQueueName, true);
    }

    @Bean
    public Queue createQueueUser() {
        return new Queue(userQueueName, true);
    }
}
