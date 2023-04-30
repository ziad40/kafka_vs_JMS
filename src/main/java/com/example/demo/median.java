package com.example.demo;

import java.io.IOException;
import java.util.Arrays;
public class median {
    public static void main(String[] args) throws IOException {
        kafka_Producer p = new kafka_Producer();
        p.run();
        long[] sendTime = new long[10000];
        sendTime = p.receiveTime;

//        System.out.println("time send : ");
//        for (int i =0;i<10;i++)
//            System.out.println(sendTime[i]);



        Kafka_Consumer c = new Kafka_Consumer();
        c.run();
        long[] receiveTime = new long[10000];
        receiveTime = c.receiveTime;

//        System.out.println("time rec");
//        for (int i =0;i<10;i++)
//            System.out.println(receiveTime[i]);


        int n = receiveTime.length;
        // Step 1: Calculate the difference between corresponding elements of the two arrays
        long[] diffArray = new long[n];
        for (int i = 0; i < n; i++) {
            diffArray[i] = Math.abs(sendTime[i] - receiveTime[i]);
        }

        // Step 2: Sort the difference array
        Arrays.sort(diffArray);

        // Step 3: Calculate the median of the difference array
        long median;
        if (n % 2 == 0) {
            median = (diffArray[n/2] + diffArray[n/2 - 1]) / 2;
        } else {
            median = diffArray[n/2];
        }

        System.out.println("Median difference is " + median);
    }
}
