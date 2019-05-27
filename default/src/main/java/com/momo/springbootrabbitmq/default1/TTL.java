package com.momo.springbootrabbitmq.default1;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class TTL {

    /**
     * 消息TTL
     * 队列TTL
     * */

    /**
     * 设置队列消息TTL
     */
    @Test
    public void testSetQueueMsgTTL() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqp://admin:njittss@dev.tss.com:5672/%2F");
        Connection connection = factory.newConnection();
        // 创建信道
        Channel channel = connection.createChannel();
        // 声明交换器
        String exchangeName = "direct.exchange.test.ttl2";
        AMQP.Exchange.DeclareOk declareOk = channel.exchangeDeclare(exchangeName, "direct", true);
        // 声明队列
        String queueName = "direct.queue.test.ttl2";
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-send-ttl", 5000);
        String ttlQueue = channel.queueDeclare(queueName, true, false, false, arguments).getQueue();
        // 绑定交换器和队列
        String routingKey = "direct.routing-key.test.ttl2";
        channel.queueBind(ttlQueue, exchangeName, routingKey);

        byte[] content = ("Test Msg " + System.currentTimeMillis()).getBytes("UTF-8");
        channel.basicPublish(exchangeName, routingKey, false, null, content);
    }

    /**
     * 设置单个消息TTL
     */
    @Test
    public void testSetSingleMsgTTL() throws Exception {
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

        // 发送消息
        byte[] content = ("Test Msg " + System.currentTimeMillis()).getBytes("UTF-8");
        AMQP.BasicProperties.Builder builder = new AMQP.BasicProperties.Builder();
        builder.deliveryMode(2);
        // 单位ms
        builder.expiration("5000");
        channel.basicPublish(exchangeName, routingKey, false, null, content);
    }

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
        String exchangeName = "direct.exchange.test.ttl";
        AMQP.Exchange.DeclareOk declareOk = channel.exchangeDeclare(exchangeName, "direct", true);
        // 声明队列
        String queueName = "direct.queue.test.ttl";
        Map<String, Object> arguments = new HashMap<>();
        // queue.declare 命令中的 x-expires 参数控制 queue 被自动删除前可以处于未使用状态的时间。
        // 未使用的意思是 queue 上没有任何 consumer ，queue 没有被重新声明，并且在过期时间段内未调用过 basic.get 命令。
        arguments.put("x-expires", 1800000);
        String ttlQueue = channel.queueDeclare(queueName, true, false, false, arguments).getQueue();
        // 绑定交换器和队列
        String routingKey = "direct.routing-key.test.ttl";
        channel.queueBind(ttlQueue, exchangeName, routingKey);

        byte[] content = ("Test Msg " + System.currentTimeMillis()).getBytes("UTF-8");
        channel.basicPublish(exchangeName, routingKey, false, null, content);
    }

}
