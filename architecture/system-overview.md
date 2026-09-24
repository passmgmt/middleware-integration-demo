# System Overview

This demo shows an event-driven order flow:

1. Clients send `POST /orders` to Kong.
2. Kong routes the request to the producer service.
3. The producer publishes an `OrderCreated` event to the `orders` Kafka topic.
4. The consumer processes the event.
5. Transient failures are retried; exhausted failures are published to `orders.dlq`.

## Components

| Component | Responsibility | Local endpoint |
| --- | --- | --- |
| Kong | API gateway and request routing | `http://localhost:8000` |
| Producer | Accepts orders and publishes events | `http://localhost:8081` |
| Redpanda | Kafka-compatible event backbone | `localhost:19092` |
| Consumer | Processes order events | `http://localhost:8082` |

The services are intentionally stateless. Kafka provides the durable boundary between accepting an order and processing it.