# Middleware Integration Demo

A lightweight but enterprise‑grade **event‑driven integration system** demonstrating API gateway routing, Kafka‑based messaging, producer/consumer microservices, DLQ handling, and cloud‑native packaging using Docker.  
Designed as a showcase of middleware, integration patterns, and distributed system engineering.

---

## 📌 Overview

This project simulates a real-world middleware integration flow commonly found in enterprise platforms. It includes:

- **API Gateway (Kong)** for routing and request mediation  
- **Producer Service** that receives API calls and publishes events  
- **Kafka / Redpanda** as the event backbone  
- **Consumer Service** that processes events  
- **DLQ (Dead Letter Queue)** for failed message handling  
- **Retry logic** for transient failures  
- **Docker Compose orchestration** for local, free deployment  
- **Architecture diagrams & sequence flows** for clarity  

This project is intentionally designed to be **simple to run locally**, yet **architecturally rich** enough to demonstrate real integration expertise.

---

## 🎯 Goals of This Project

This demo highlights your ability to design and implement:

- Event-driven architecture  
- Middleware patterns (gateway → topic → consumer)  
- Reliable message processing (retry + DLQ)  
- API gateway configuration  
- Microservice communication  
- Cloud-native packaging (Docker)  
- Enterprise documentation (architecture, sequence diagrams, ADRs)  

---

## 🏗️ Architecture

### System Overview

```mermaid
flowchart LR
    A[API Gateway - Kong] -->|POST /orders| B[Producer Service]
    B -->|Publish Event| C[(Kafka / Redpanda)]
    C --> D[Consumer Service]
    D -->|Success| E[Order Processed]
    D -->|Failure| F[DLQ Topic]
