package com.momo.springbootrabbitmq.default1.send;

import com.rabbitmq.client.*;
import org.junit.Test;

public class SendMessage {

    @Test
    public void testSendDirectMsg() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqp://admin:njittss@dev.tss.com:5672/%2F");
        Connection connection = factory.newConnection();
        // 创建信道
        Channel channel = connection.createChannel();
        // 声明交换器
        String exchangeName = "direct.exchange.test.send";
        AMQP.Exchange.DeclareOk declareOk = channel.exchangeDeclare(exchangeName, "direct", true);
        // 声明队列
        String queueName = "direct.queue.test.send";
        String ttlQueue = channel.queueDeclare(queueName, true, false, false, null).getQueue();
        // 绑定交换器和队列
        String routingKey = "direct.routing-key.test.send";
        channel.queueBind(ttlQueue, exchangeName, routingKey);

        byte[] content = "Test Msg".getBytes("UTF-8");

        // 普通消息，使用默认消息属性
        channel.basicPublish(exchangeName, routingKey, false, null, content);

        // 设置消息属性，投递模式是2，即消息会持
        // 久化存入服务器；消息优先级设置为0；content-type为"text/plain"
        AMQP.BasicProperties props = MessageProperties.TEXT_PLAIN;
        channel.basicPublish(exchangeName, routingKey, false, props, content);

        // 自定义消息属性
        AMQP.BasicProperties props2 = new AMQP.BasicProperties.Builder()
                .contentType("text/plain")
                .deliveryMode(2)
                .priority(0)
                .build();
        channel.basicPublish(exchangeName, routingKey, false, props2, content);
    }


    @Test
    public void testSendDirectMsgWithExpiration() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqp://admin:njittss@dev.tss.com:5672/%2F");
        Connection connection = factory.newConnection();
        // 创建信道
        Channel channel = connection.createChannel();
        // 声明交换器
        String exchangeName = "direct.exchange.test.send";
        AMQP.Exchange.DeclareOk declareOk = channel.exchangeDeclare(exchangeName, "direct", true);
        // 声明队列
        String queueName = "direct.queue.test.send";
        String ttlQueue = channel.queueDeclare(queueName, true, false, false, null).getQueue();
        // 绑定交换器和队列
        String routingKey = "direct.routing-key.test.send";
        channel.queueBind(ttlQueue, exchangeName, routingKey);

        byte[] content = "Test Msg".getBytes("UTF-8");

        // 自定义消息属性，设置过期时间
        AMQP.BasicProperties props = new AMQP.BasicProperties.Builder()
                .expiration("60000")
                .build();
        channel.basicPublish(exchangeName, routingKey, false, props, content);
    }
}
