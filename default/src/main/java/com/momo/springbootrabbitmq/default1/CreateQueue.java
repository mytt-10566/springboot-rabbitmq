package com.momo.springbootrabbitmq.default1;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.junit.Test;


public class CreateQueue {

    @Test
    public void testSendDirectMsg() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqp://admin:njittss@dev.tss.com:5672/%2F");
        Connection connection = factory.newConnection();
        // 创建信道
        Channel channel = connection.createChannel();
        // 声明交换器
        String exchangeName = "direct.exchange.test.ttl";
        AMQP.Exchange.DeclareOk declareOk = channel.exchangeDeclare(exchangeName, "direct", true);
        // 声明队列
        String queueName = "direct.queue.test.ttl";
        String ttlQueue = channel.queueDeclare(queueName, true, false, false, null).getQueue();
        // 绑定交换器和队列
        String routingKey = "direct.routing-key.test.ttl";
        channel.queueBind(ttlQueue, exchangeName, routingKey);

        byte[] content = "Test Msg".getBytes("UTF-8");
        channel.basicPublish(exchangeName, routingKey, false, null, content);
    }

}
