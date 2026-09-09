# Task Tracker (Java Spring Boot REST API)

A task management REST API for creating, updating, and managing tasks. The project demonstrates REST API development with Spring Boot, database integration with PostgreSQL, validation, exception handling, testing, and basic API documentation.

## Features

* Create new tasks
* Get all tasks
* Get a task by ID
* Update tasks
* Start tasks
* Mark tasks as done
* Delete tasks
* Search tasks by title
* Filter tasks by status ("TODO", "IN_PROGRESS", "DONE")
* Filter tasks by priority ("LOW", "MEDIUM", "HIGH")
* Get overdue tasks
* Track task history ("CREATED", "STARTED", "COMPLETED")
* Persistent storage with PostgreSQL
* Automatic task ID generation
* Input validation
* Custom exception handling
* Swagger/OpenAPI documentation

## Tech Stack

* Java 21
* Spring Boot
* Spring Web
* Spring Data JPA
* Spring Validation
* PostgreSQL
* Maven
* JUnit
* Mockito
* Swagger/OpenAPI
* Docker Compose
* REST API
* OOP
* Git/GitHub

## Project Structure

controller   → REST API endpoints
dto          → request objects for creating and updating tasks
exception    → custom exceptions and global exception handler
model        → entities and enums
repository   → Spring Data JPA repositories
service      → business logic

TaskTrackerApplication → main Spring Boot application class

## Getting Started

1. Clone the repository.
2. Make sure Docker Desktop is installed and running.
3. Start PostgreSQL with Docker Compose.
4. Set the "DB_PASSWORD" environment variable with your PostgreSQL password.
5. Open the project in IntelliJ IDEA.
6. Run the Spring Boot application.

The API will be available at:

http://localhost:8080

Swagger UI is available at:

http://localhost:8080/swagger-ui/index.html

## Database

The application uses PostgreSQL for persistent storage.

Docker Compose starts the PostgreSQL container and creates two databases:

* task_tracker — main application database
* task_tracker_test — test database

The application uses the "DB_PASSWORD" environment variable instead of storing the database password directly in the project.

## API

Main endpoints include:

| Method | Endpoint                     | Description        |
| ------ | ---------------------------- | ------------------ |
| POST   | `/tasks`                     | Create a task      |
| GET    | `/tasks`                     | Get all tasks      |
| GET    | `/tasks/{id}`                | Get a task         |
| PUT    | `/tasks/{id}`                | Update a task      |
| PUT    | `/tasks/{id}/start`          | Start a task       |
| PUT    | `/tasks/{id}/complete`       | Complete a task    |
| DELETE | `/tasks/{id}`                | Delete a task      |
| GET    | `/tasks/search?keyword=...`  | Search by title    |
| GET    | `/tasks/status/{status}`     | Filter by status   |
| GET    | `/tasks/priority/{priority}` | Filter by priority |
| GET    | `/tasks/overdue`             | Get overdue tasks  |
| GET    | `/tasks/{id}/history`        | Get task history   |

## Tests

The project includes:

* Repository tests
* Service tests
* Controller tests

Tests use a separate PostgreSQL database: "task_tracker_test".

## What I Learned

* Building REST APIs with Spring Boot
* Working with HTTP methods and status codes
* Separating controller and service responsibilities
* Working with Spring Data JPA
* Connecting a Spring Boot application to PostgreSQL
* Working with DTOs
* Input validation
* Exception handling with @RestControllerAdvice
* Writing repository, service, and controller tests
* API documentation with Swagger/OpenAPI
* Working with Docker Compose
* Git workflow

## Project Status

The project is complete and includes the main functionality of a task management REST API, database persistence, validation, exception handling, testing, task history, Swagger documentation, and Docker-based PostgreSQL setup.

