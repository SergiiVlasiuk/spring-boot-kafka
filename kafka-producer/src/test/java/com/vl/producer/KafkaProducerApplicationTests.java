package com.vl.producer;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@EmbeddedKafka(partitions = 1, topics = {"${kafka-conf.demo-topic}"})
@ActiveProfiles("test")
class KafkaProducerApplicationTests {

	@Test
	void contextLoads() {
	}

}
