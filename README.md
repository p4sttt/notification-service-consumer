# 📬 Notification Service Consumer

Implementation of the email notification service using the kafka message broker

## Feature

### Kafka message broker

Kafka allows you to reduce the load on the main server and ensure reliable communication between microservices, which allows you to correctly distribute the work of the system to different services.

### Email service

Email service allows you to send emails to users which you have subscribed to.

## Installation & Usage

### Pre-requisites

- [java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) or higher
- [maven 3.8.6](https://maven.apache.org/install.html) or higher
- [kafka 3.3.1](https://kafka.apache.org/downloads) or higher

### Installation

```bash
    git clone https://github.com/p4sttt/notification-service-consumer.git
    cd notification-service-consumer
    mvn clean install
```

### Usage

1. Change `src/main/resources/application.properties` file to match your environment

2. Run `mvn spring-boot:run`