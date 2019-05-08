package com.momo.springbootrabbitmq.default1;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.junit.Assert;
import org.junit.Test;

public class Connect {

    /**
     * 测试获取连接
     * */
    @Test
    public void testConnect() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("admin");
        factory.setPassword("njittss");
        factory.setVirtualHost("/");
        factory.setHost("dev.tss.com");
        factory.setPort(5672);
        Connection connection = factory.newConnection();
        Assert.assertNotNull(connection);
    }

    @Test
    public void testConnect2() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqp://admin:njittss@dev.tss.com:5672/%2F");
        Connection connection = factory.newConnection();
        Assert.assertNotNull(connection);
    }
}
