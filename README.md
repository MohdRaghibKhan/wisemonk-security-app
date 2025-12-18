# User Management System with RBAC

This project is a User Management System built using Spring Boot and MySQL.
It implements secure authentication, role-based access control (RBAC), and event-driven
communication using RabbitMQ.

The system is designed to be secure, modular, scalable, and easy to deploy using Docker.

--------------------------------------------------
OBJECTIVE
--------------------------------------------------

Design and implement a secure backend system that supports user registration, authentication,
role management, and admin-level operations while following industry best practices.

--------------------------------------------------
TECH STACK
--------------------------------------------------

Java 17  
Spring Boot 3.x  
Spring Security (JWT, BCrypt)  
MySQL (Spring Data JPA, Hibernate)  
RabbitMQ (Topic Exchange)  
Docker & Docker Compose  
Maven  

--------------------------------------------------
APPLICATION ARCHITECTURE
--------------------------------------------------

The application follows a layered architecture:

Controller → Service → Repository

This ensures clear separation of concerns, better maintainability, and easier testing.

--------------------------------------------------
SECURITY DESIGN
--------------------------------------------------

Authentication is stateless and implemented using JWT.
JWT tokens contain user identity and assigned roles and are passed via the Authorization header.

Passwords are securely hashed using BCrypt before storing in the database.

Role-Based Access Control (RBAC) is implemented using Spring Security and method-level
authorization with @PreAuthorize.

--------------------------------------------------
FUNCTIONAL FEATURES
--------------------------------------------------

User Registration  
POST /api/users/register  
- Accepts username, email, and password  
- Email uniqueness is validated  
- Password is stored in hashed form  
- Publishes a user.register event  

User Login  
POST /api/users/login  
- Accepts email and password  
- Returns JWT token  
- Publishes a user.login event  

View Current User  
GET /api/users/me  
- Requires JWT token  
- Returns user details and roles  

Role Management (Admin Only)  
POST /api/roles  
POST /api/users/{userId}/roles  

Admin Endpoint  
GET /api/admin/stats  
- Accessible only by ADMIN role  
- Returns mock statistics  

--------------------------------------------------
DATABASE SETUP
--------------------------------------------------

MySQL is used as the primary database in both local and Docker environments.

Tables include:
- users
- roles
- user_roles

IMPORTANT:
Before the first user registration, a default role must be created manually.

Example:
INSERT INTO roles (name) VALUES ('USER');

--------------------------------------------------
EVENT-DRIVEN DESIGN (RABBITMQ)
--------------------------------------------------

RabbitMQ is used to publish events for key user actions.

A Topic Exchange is configured with:
- One queue
- Two routing keys:
  - user.register
  - user.login

Both events are routed to the same queue, enabling centralized and scalable event handling.

--------------------------------------------------
CONFIGURATION MANAGEMENT
--------------------------------------------------

Spring Profiles are used for environment-specific configuration.

application.properties  
- Used only for profile routing  
- Contains: spring.profiles.active=local  

application-local.properties  
- Local MySQL and RabbitMQ configuration  

application-docker.properties  
- Docker container-based configuration  

--------------------------------------------------
DOCKER SUPPORT
--------------------------------------------------

Docker Compose is used to run the complete system with:
- Spring Boot application
- MySQL database
- RabbitMQ message broker

This ensures consistent environments and production-like local setup.
Steps:
1. Build the application
.\mvnw clean package -DskipTests   

2. Start containers
docker-compose up --build
--------------------------------------------------
DESIGN DECISIONS
--------------------------------------------------

A stateless JWT-based security model is used for scalability.

RBAC is implemented to ensure strict access control.

DTO-based API design prevents exposing entities directly.

Global exception handling and validation provide clean and consistent error responses.

Event-driven architecture allows loose coupling and future extensibility.

--------------------------------------------------
SUMMARY
--------------------------------------------------

Secure JWT-based authentication  
Role-based access control  
Event publishing using RabbitMQ  
Environment-specific configuration  
Docker-ready and production-oriented design  

--------------------------------------------------
AUTHOR
--------------------------------------------------

Mohd Raghib Khan  
Java Backend Developer
