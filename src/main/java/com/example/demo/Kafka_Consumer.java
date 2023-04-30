package com.example.demo;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.StringDeserializer;


public class Kafka_Consumer {
    long[] receiveTime = new long[10000];
    private static final String TOPIC = "my-topic";
    private static final String BOOTSTRAP_SERVERS = "localhost:9092";
    private static final String GROUP_ID = "my-group";

    public void run() {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());


        final Consumer<String, String> consumer = new KafkaConsumer<>(props);

        consumer.subscribe(Collections.singleton(TOPIC));

        double sum = 0;
        int msgReceived = 0;

        while (true) {
            final ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofMillis(1000));

            if (consumerRecords.isEmpty()) {
                continue;
            }
            for (ConsumerRecord<String, String> record : consumerRecords) {
                long startTime = System.currentTimeMillis();
                System.out.printf("Consumer Record:(%s, %s, %d, %d)\n",
                        record.key(), record.value(),
                        record.partition(), record.offset());
                System.out.println("message received");
                sum += (System.currentTimeMillis() - startTime);
                receiveTime[msgReceived] = record.timestamp();
                msgReceived++;
            }
            System.out.println("message rec : " + msgReceived);
            consumer.commitAsync();
            if (msgReceived >= 10000) { // stop after receiving 1000 messages
                break;
            }
        }
        System.out.println("average response time = " + (sum / msgReceived) + " ms");
        consumer.close();

    }
}
