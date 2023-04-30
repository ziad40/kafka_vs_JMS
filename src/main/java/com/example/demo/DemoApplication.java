package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
//		kafka_Producer producer = new kafka_Producer();
//		producer.run();
//		Kafka_Consumer consumer = new Kafka_Consumer();
//		consumer.run();
	}

}
