package com.momo.springbootrabbitmq.default1;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class AlternateExchange {

    /**
     * 设置队列TTL
     */
    @Test
    public void testAlternateExchange() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqp://admin:njittss@dev.tss.com:5672/%2F");
        Connection connection = factory.newConnection();
        // 创建信道
        Channel channel = connection.createChannel();

        // 声明交换器
        String altExchangeName = "direct.exchange.test.alt";
        String exchangeName = "direct.exchange.test.alt-normal";

        String altQueueName = "direct.queue.test.alt";
        String queueName = "direct.queue.test.alt-normal";

        String altRoutingKey = "direct.routing-key.test.alt";
        String routingKey = "direct.routing-key.test.alt-normal";

        Map<String, Object> args = new HashMap<>();
        args.put("alternate-exchange", altExchangeName);
        AMQP.Exchange.DeclareOk declareOk = channel.exchangeDeclare(exchangeName, "direct", true, false, args);
        AMQP.Exchange.DeclareOk fanoutDeclareOK = channel.exchangeDeclare(altExchangeName, "fanout", true, false, null);

        // 声明队列
        String queue = channel.queueDeclare(queueName, true, false, false, null).getQueue();
        String altQueue = channel.queueDeclare(altQueueName, true, false, false, null).getQueue();

        // 绑定交换器和队列
        channel.queueBind(queue, exchangeName, routingKey);
        channel.queueBind(altQueue, altExchangeName, altRoutingKey);

        // 正常路由，进入队列
        byte[] content = ("Test Msg " + System.currentTimeMillis()).getBytes("UTF-8");
        channel.basicPublish(exchangeName, routingKey, false, null, content);

        // 不可路由，进入备份交换器
        byte[] content2 = ("Test Msg2 " + System.currentTimeMillis()).getBytes("UTF-8");
        channel.basicPublish(exchangeName, routingKey + "2", false, null, content2);
    }

}
