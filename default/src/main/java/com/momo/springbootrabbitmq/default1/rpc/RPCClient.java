package com.momo.springbootrabbitmq.default1.rpc;

import com.rabbitmq.client.*;

import java.util.UUID;

public class RPCClient {

    private Connection connection;
    private Channel channel;
    private String requestQueueName = "rpc_queue";
    private String replyQueueName;
    private QueueingConsumer consumer;

    public RPCClient() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqp://admin:njittss@dev.tss.com:5672/%2F");
        connection = factory.newConnection();
        // 创建信道
        channel = connection.createChannel();

        channel.queueDeclare(requestQueueName, true, false, false, null);

        replyQueueName = channel.queueDeclare().getQueue();
        channel.basicConsume(replyQueueName, true, consumer);
    }

    public String call(String message) throws Exception {
        String response  = null;
        String corrId = UUID.randomUUID().toString();

        AMQP.BasicProperties props = new AMQP.BasicProperties()
                .builder()
                .correlationId(corrId)
                .replyTo(replyQueueName)
                .build();
        channel.basicPublish("", requestQueueName, props, message.getBytes());

        while (true) {
            if (consumer != null) {
                QueueingConsumer.Delivery delivery = consumer.nextDelivery();
                if (delivery.getProperties().getCorrelationId().equals(corrId)) {
                    response = new String(delivery.getBody());
                    break;
                }
            }
        }

        return response;
    }

    public void close() throws Exception {
        connection.close();
    }

    public static void main(String[] args) throws Exception {
        RPCClient client = new RPCClient();
        String response = client.call("30");
        System.out.println(response);
        client.close();
    }
}
