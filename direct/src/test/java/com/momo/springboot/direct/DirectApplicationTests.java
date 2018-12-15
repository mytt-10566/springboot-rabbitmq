package com.momo.springboot.direct;

import com.momo.springboot.direct.producer.DirectProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DirectApplicationTests {

    @Autowired
    private DirectProducer producer;

    @Test
    public void testSendDirectMsg() {
        producer.send();
    }

}

