package com.momo.springbootrabbitmq.fanout;

import com.momo.springbootrabbitmq.fanout.producer.FanoutProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FanoutApplicationTests {
	
	@Autowired
	private FanoutProducer producer;

	@Test
	public void testSendFanoutMsg() {
		producer.send();
	}

}
