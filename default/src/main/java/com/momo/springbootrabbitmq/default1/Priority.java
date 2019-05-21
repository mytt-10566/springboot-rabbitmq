package com.momo.springbootrabbitmq.default1;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class Priority {

    /**
     * 设置队列TTL
     */
    @Test
    public void testSetQueueTTL() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqp://admin:njittss@dev.tss.com:5672/%2F");
        Connection connection = factory.newConnection();
        // 创建信道
        Channel channel = connection.createChannel();
        // 声明交换器
        String exchangeName = "direct.exchange.test.priority";
        AMQP.Exchange.DeclareOk declareOk = channel.exchangeDeclare(exchangeName, "direct", true);
        // 声明队列
        String queueName = "direct.queue.test.priority";
        Map<String, Object> arguments = new HashMap<>();
        // 定义队列优先的最大优先级最大为10
        arguments.put("x-max-priority", 10);
        String ttlQueue = channel.queueDeclare(queueName, true, false, false, arguments).getQueue();
        // 绑定交换器和队列
        String routingKey = "direct.routing-key.test.priority";
        channel.queueBind(ttlQueue, exchangeName, routingKey);

        byte[] content = ("Test Msg " + System.currentTimeMillis()).getBytes("UTF-8");
        // 设置消息的优先级为8
        AMQP.BasicProperties.Builder builder = new AMQP.BasicProperties.Builder();
        builder.priority(8);
        channel.basicPublish(exchangeName, routingKey, false, null, content);
    }

}
