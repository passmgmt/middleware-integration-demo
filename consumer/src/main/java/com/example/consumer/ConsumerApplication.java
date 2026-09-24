package com.example.consumer;

import java.time.Instant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class ConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }

    @Component
    static class OrderListener {
        @KafkaListener(topics = "orders", groupId = "order-processor")
        void process(OrderCreated event, Acknowledgment acknowledgment) {
            System.out.printf("Processed order %s for customer %s at %s%n", event.orderId(), event.customerId(), Instant.now());
            acknowledgment.acknowledge();
        }
    }

    record OrderCreated(String orderId, String customerId, double amount, Instant createdAt) {}
}