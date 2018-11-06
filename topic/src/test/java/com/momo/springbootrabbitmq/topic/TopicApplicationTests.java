package com.momo.springbootrabbitmq.topic;

import com.momo.springbootrabbitmq.topic.producer.TopicProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TopicApplicationTests {
	
	@Autowired
	private TopicProducer producer;

	@Test
	public void testSendTopicMsg() {
		producer.send();
	}

}
