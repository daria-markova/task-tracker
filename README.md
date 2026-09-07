# Task Tracker (Java Spring Boot REST API)

A task management REST API that allows users to create, update, delete, and manage tasks. The project demonstrates REST API development, object-oriented programming, database integration, validation, and exception handling.

## Features
- Create new tasks
- Get all tasks
- Get a task by ID
- Update tasks
- Start tasks
- Mark tasks as done
- Delete tasks
- Search tasks by title
- Filter tasks by:
      - Status (TODO/IN_PROGRESS/DONE)
      - Priority (LOW/MEDIUM/HIGH)
- Get overdue tasks
- Persistent task storage with PostgreSQL
- Automatic task ID generation by the database
- Input validation
- Custom exception handling

## Tech Stack
- Java 21
- Spring Boot 
- Spring Web
- Spring Data JPA
- Spring Validation
- PostgreSQL
- Maven
- JUnit
- OOP (Encapsulation, Abstraction)
- REST API
- Git / GitHub

## Project Structure
controller → REST API endpoints (TaskController)

dto → request objects for creating and updating tasks 

exception → custom exceptions and global exception handler 

model → task model and enums 

service → business logic 

TaskTrackerApplication → main Spring Boot application class

## Getting Started
1) Clone the repository:
https://github.com/daria-markova/task-tracker.git
2) Create a PostgreSQL database named task_tracker.
3) Set the DB_PASSWORD environment variable with your PostgreSQL password.
4) Open the project in IntelliJ IDEA.
5) Run the Spring Boot application.
6) The API will be available at http://localhost:8080.

## Tests
The project includes repository, service, and controller tests.

The tests use a separate PostgreSQL database:
task_tracker_test

## What I learned
- Building REST APIs with Spring Boot
- Working with HTTP methods and status codes
- Separating controller and service responsibilities
- Working with Spring Data JPA
- Connecting a Spring Boot application to PostgreSQL
- Working with DTOs
- Input validation
- Exception handling with @RestControllerAdvice
- Writing repository, service, and controller tests
- Git workflow

## Future Improvements
- Add task history
- Add API documentation with Swagger / OpenAPI
- Add Docker support
- Improve database queries with Spring Data JPA
