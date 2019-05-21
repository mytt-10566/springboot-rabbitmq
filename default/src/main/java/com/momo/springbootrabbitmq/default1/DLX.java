package com.momo.springbootrabbitmq.default1;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class DLX {

    @Test
    public void testDLX() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqp://admin:njittss@dev.tss.com:5672/%2F");
        Connection connection = factory.newConnection();
        // 创建信道
        Channel channel = connection.createChannel();

        String exchangeName = "direct.exchange.test.dlx.normal";
        String dlxExchangeName = "direct.exchange.test.dlx";

        String queueName = "direct.queue.test.dlx.normal";
        String dlxQueueName = "direct.queue.test.dlx";

        String routingKey = "direct.routing-key.test.dlx.normal";
        String dlxRoutingKey = "direct.routing-key.test.dlx";

        // 普通交换器、队列
        AMQP.Exchange.DeclareOk declareOk = channel.exchangeDeclare(exchangeName, "direct", true);
        // 声明队列
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-message-ttl", 5000);
        arguments.put("x-dead-letter-exchange", dlxExchangeName);
        arguments.put("x-dead-letter-routing-key", dlxRoutingKey);
        String queue = channel.queueDeclare(queueName, true, false, false, arguments).getQueue();
        // 绑定交换器和队列
        channel.queueBind(queue, exchangeName, routingKey);

        // 死信交换器、队列
        AMQP.Exchange.DeclareOk dlxDeclareOk = channel.exchangeDeclare(dlxExchangeName, "direct", true);
        // 声明队列
        String dlxQueue = channel.queueDeclare(dlxQueueName, true, false, false, null).getQueue();
        // 绑定交换器和队列
        channel.queueBind(dlxQueue, dlxExchangeName, dlxRoutingKey);

        byte[] content = "Test Msg".getBytes("UTF-8");
        channel.basicPublish(exchangeName, routingKey, false, null, content);
    }

}
