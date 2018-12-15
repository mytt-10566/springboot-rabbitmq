package com.momo.springbootrabbitmq.default0;

import com.momo.springbootrabbitmq.default0.po.User;
import com.momo.springbootrabbitmq.default0.queue.Producer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SingleApplicationTests {

    @Autowired
    private Producer producer;

    // 发送字符串
    @Test
    public void testSendMsg() {
        for (int i = 0; i < 10; i++) {
            producer.sendMsg1("小明" + i);
            producer.sendMsg2("小明" + i);
        }
    }

    // 发送java Object
    @Test
    public void testSendUser() {
        for (int i = 0; i < 10; i++) {
            producer.sendUser(new User(Long.valueOf(i), "小明" + i));
        }
    }
}
