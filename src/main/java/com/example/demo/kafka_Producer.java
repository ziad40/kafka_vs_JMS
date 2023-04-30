package com.example.demo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class kafka_Producer {
    long[] receiveTime = new long[10000];
    public void run() throws IOException {
        String messageFilePath = "E:/message.txt";
        String topicName = "my-topic";
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);
        String message = new String(Files.readAllBytes(Paths.get(messageFilePath)));
        System.out.println("message size : " + message.length());
        ProducerRecord<String, String> record = new ProducerRecord<>(topicName, message);

        double sum = 0;
        int msgToSent = 10000;
        for(int i = 0; i < msgToSent; i++) {
            long time = System.currentTimeMillis();
            // Send the message

            producer.send(record);
            receiveTime[i] = System.currentTimeMillis();
            sum += (System.currentTimeMillis() - time);
        }
        System.out.println("for message: " + record.toString());

        producer.close();
        System.out.println("average response time = " + (sum / msgToSent) + " ms");
        System.out.println("sent " + msgToSent + " messages");
    }
}
