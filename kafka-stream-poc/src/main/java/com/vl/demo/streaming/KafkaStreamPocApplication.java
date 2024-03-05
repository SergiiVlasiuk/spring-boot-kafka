package com.vl.demo.streaming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.schema.client.EnableSchemaRegistryClient;

@SpringBootApplication
//@EnableSchemaRegistryClient
public class KafkaStreamPocApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaStreamPocApplication.class, args);
	}

}
