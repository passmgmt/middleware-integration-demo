package com.example.producer;

import java.time.Instant;
import java.util.UUID;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class ProducerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProducerApplication.class, args);
    }

    @RestController
    @RequestMapping("/orders")
    static class OrderController {
        private final KafkaTemplate<String, OrderCreated> kafkaTemplate;

        OrderController(KafkaTemplate<String, OrderCreated> kafkaTemplate) {
            this.kafkaTemplate = kafkaTemplate;
        }

        @PostMapping
        ResponseEntity<OrderCreated> create(@RequestBody CreateOrder request) {
            var event = new OrderCreated(UUID.randomUUID().toString(), request.customerId(), request.amount(), Instant.now());
            kafkaTemplate.send("orders", event.orderId(), event);
            return ResponseEntity.accepted().body(event);
        }
    }

    record CreateOrder(String customerId, double amount) {}

    record OrderCreated(String orderId, String customerId, double amount, Instant createdAt) {}
}