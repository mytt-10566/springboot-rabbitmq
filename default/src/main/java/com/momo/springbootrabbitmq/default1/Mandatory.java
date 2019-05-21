package com.momo.springbootrabbitmq.default1;

import com.rabbitmq.client.*;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Mandatory {

    /**
     * mandatory参数
     */
    @Test
    public void testMandatory() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqp://admin:njittss@dev.tss.com:5672/%2F");
        Connection connection = factory.newConnection();
        // 创建信道
        Channel channel = connection.createChannel();
        // 声明交换器
        String exchangeName = "direct.exchange.test.mandatory";
        AMQP.Exchange.DeclareOk declareOk = channel.exchangeDeclare(exchangeName, "direct", true);
        // 声明队列
        String queueName = "direct.queue.test.mandatory";
        Map<String, Object> arguments = new HashMap<>();
        String queue = channel.queueDeclare(queueName, true, false, false, null).getQueue();
        // 绑定交换器和队列
        String routingKey = "direct.routing-key.test.mandatory";
        channel.queueBind(queue, exchangeName, routingKey);

        /*// 正常路由的消息
        byte[] content = ("Test Msg " + System.currentTimeMillis()).getBytes("UTF-8");
        channel.basicPublish(exchangeName, routingKey, true, null, content);

        // 不可路由的消息
        byte[] content2 = ("Test Msg2 " + System.currentTimeMillis()).getBytes("UTF-8");
        channel.basicPublish(exchangeName, routingKey + "2", true, null, content2);*/

        // 正常路由的消息
        channel.basicPublish(exchangeName, routingKey, true, MessageProperties.TEXT_PLAIN, "Test Msg".getBytes("UTF-8"));

        // 正常路由的消息
        channel.basicPublish(exchangeName, routingKey + "2", true, MessageProperties.TEXT_PLAIN, "Test Msg2".getBytes("UTF-8"));

        channel.addReturnListener(new ReturnListener() {
            @Override
            public void handleReturn(int replyCode, String replyText, String exchange, String routingKey, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(message);
            }
        });
    }
}
