# Spring Boot Hotel Reservation

Spring Boot REST API for Hotel Reservation system.

## Overview
This repository contains a Java Spring Boot application that provides REST endpoints for managing hotels, rooms, reservations, and customers.

## Prerequisites
- Java 17 (or the Java version declared in pom.xml)
- Maven (or use the included Maven wrapper `./mvnw`)
- Git

## Build & Run
Build:
```
./mvnw clean package
# or
mvn clean package
```

Run:
```
# using the packaged jar
java -jar target/*.jar

# or from source
./mvnw spring-boot:run
```

## Tests
Run unit/integration tests:
```
./mvnw test
```

## Endpoints
Add your main endpoints here (examples):
- GET /api/hotels — list hotels
- GET /api/hotels/{id} — get hotel details
- POST /api/reservations — create reservation
- GET /api/reservations/{id} — get reservation

## Contributing
- Create a feature branch: `git checkout -b feat/your-feature`
- Commit, push, and open a PR
- Add tests for new features

## License
Add a license file (e.g. MIT, Apache-2.0) or state the project license here.
