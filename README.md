Hotel Reservation API (Spring Boot)

A simple Spring Boot REST API for managing hotel rooms, customers, and reservations.
This project demonstrates backend development using Spring MVC, JPA, and a clean layered architecture.

🚀 Features

CRUD operations for rooms, customers, and reservations

RESTful API design

JPA/Hibernate persistence

H2 in-memory database (default)

🛠 Tech Stack

Java

Spring Boot

Spring Web

Spring Data JPA

H2 Database

Maven

▶️ Run the Project
mvn spring-boot:run


The application starts at:
http://localhost:8080

📌 Example Endpoints

Rooms

GET /api/rooms

POST /api/rooms

Customers

GET /api/customers

POST /api/customers

Reservations

GET /api/reservations

POST /api/reservations

📂 Structure

controller/ – REST endpoints

service/ – business logic

repository/ – JPA repositories

entity/ – data models
