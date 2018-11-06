package com.momo.springbootrabbitmq.callback;

import com.momo.springbootrabbitmq.callback.producer.Producer;
import com.momo.springbootrabbitmq.callback.producer.ProducerCallback;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CallbackApplicationTests {

	@Autowired
	private Producer producer;
	
	@Test
	public void testSendCallBackMsg() {
		producer.send();
	}

}
