package com.momo.springbootrabbitmq.default1.rpc;

import com.rabbitmq.client.*;

import java.io.IOException;

public class RPCServer {

    private static final String RPC_QUEUE_NAME = "rpc_queue";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqp://admin:njittss@dev.tss.com:5672/%2F");
        Connection connection = factory.newConnection();
        // 创建信道
        Channel channel = connection.createChannel();

        channel.queueDeclare(RPC_QUEUE_NAME, true, false, false, null);

        Consumer consumer = new DefaultConsumer(channel) {

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                AMQP.BasicProperties replyProps = new AMQP.BasicProperties()
                    .builder()
                    .correlationId(properties.getCorrelationId())
                    .build();

                String response = "";

                try {
                    String message =  new String(body, "UTF-8");
                    int n = Integer.parseInt(message);
                    response += fib(n);
                } catch (RuntimeException e) {
                    e.printStackTrace();
                } finally {
                    channel.basicPublish("", properties.getReplyTo(), replyProps, response.getBytes("UTF-8"));
                    channel.basicAck(envelope.getDeliveryTag(), false);
                }
            }

        };
        channel.basicConsume(RPC_QUEUE_NAME, false, consumer);
    }

    private static int fib(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return fib(n - 1) + fib(n - 2);
    }

}
