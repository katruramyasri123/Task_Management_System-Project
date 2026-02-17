The Task Management System is a backend application built using Microservices Architecture to manage users, tasks, and task submissions in a scalable and modular way.

The system demonstrates real-world distributed system design using:

Service Discovery (Eureka)

API Gateway (Spring Cloud Gateway)

Inter-Service Communication (OpenFeign)

Stateless Authentication (JWT)

Database per Service pattern

This project simulates a production-level backend architecture used in modern enterprise applications.

🎯 Key Features

✅ Secure User Registration & Login (JWT Authentication)

✅ Role-based Authorization

✅ Create, Update, Delete Tasks

✅ Assign Deadlines & Track Task Status

✅ Submit Task Solutions (GitHub Link)

✅ Accept / Reject Submissions

✅ API Gateway Centralized Routing

✅ Service Discovery with Eureka

✅ Secure Inter-service Communication

🏗️ Microservices Architecture

The system consists of the following services:

Service	Port	Description
Eureka Server	8070	Service Registry
API Gateway	5000	Central Routing Layer
User Service	5001	Authentication & User Management
Task Service	5002	Task Operations
Submission Service	5003	Task Submission Handling
🔄 System Flow
Client (React / Postman)
        |
        v
API Gateway (5000)
        |
   -------------------------
   |          |            |
   v          v            v
User       Task       Submission
Service    Service     Service


All requests go through the Gateway.

Gateway routes to respective service.

Services communicate via OpenFeign.

Each service maintains its own database.

🔐 Security Implementation

JWT-based Stateless Authentication

BCrypt Password Encryption

Custom JWT Filter

Authorization Header:

Authorization: Bearer <JWT_TOKEN>


Token forwarding between services using Feign Client

🗄️ Database Design

Each service follows Database per Service Pattern:

User Service → users table

Task Service → tasks table

Submission Service → submissions table

No foreign keys are used between services.
Relationships are maintained logically via IDs.

🔧 Technologies Used
Backend

Java 17

Spring Boot 3

Spring Security

Spring Cloud

Eureka Server

Spring Cloud Gateway

OpenFeign

Hibernate / JPA

JWT

Database

MySQL

Tools

Maven

Postman

Git & GitHub

📡 API Sample Endpoints
Authentication
POST /auth/signup
POST /auth/signin

Task APIs
POST /api/tasks/save
GET /api/tasks
PUT /api/tasks/{id}
DELETE /api/tasks/{id}

Submission APIs
POST /api/submission/submit
GET /api/submission/{id}
PUT /api/submission/{id}?status=ACCEPTED

⚙️ How to Run the Project

Start Eureka Server (Port 8070)

Start User Service

Start Task Service

Start Submission Service

Start API Gateway

Access APIs via:

http://localhost:5000

🧠 Challenges Solved

Fixed CORS policy issues between frontend and backend

Resolved 503 No Instance Available (Service Name Mismatch)

Handled JWT forwarding in Feign Client

Debugged 500 Internal Server Errors

Solved Enum conversion issues

Managed independent service communication

📈 What This Project Demonstrates

Strong understanding of Microservices Architecture

Real-world backend debugging skills

Secure authentication implementation

Production-style service communication

Scalable system design
