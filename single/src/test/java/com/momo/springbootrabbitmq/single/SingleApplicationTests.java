package com.momo.springbootrabbitmq.single;

import com.momo.springbootrabbitmq.single.po.User;
import com.momo.springbootrabbitmq.single.queue.Producer;
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

    @Test
    public void testSendMsg() {
        for (int i = 0; i < 10; i++) {
            producer.sendMsg1("小明" + i);
            producer.sendMsg2("小明" + i);
        }
    }

    @Test
    public void testSendUser() {
        for (int i = 0; i < 10; i++) {
            producer.sendUser(new User(Long.valueOf(i), "小明" + i));
        }
    }

}
