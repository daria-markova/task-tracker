# Task Tracker (Java Spring Boot REST API)

A task management REST API that allows users to create, update, delete, and manage tasks. The project demonstrates object-oriented programming principles, REST API development, exception handling, and backend development fundamentals.

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
- Persistent task storage in JSON
- Automatic task ID generation
- Input validation
- Custom exception handling

## Tech Stack
- Java 21
- Spring Boot 
- Spring Web
- Spring Validation
- Maven
- Jackson
- OOP (Encapsulation, Abstraction)
- Collections (ArrayList)
- REST API
- JSON
- Git / GitHub

## Project Structure
controller → REST API endpoints (TaskController)

dto → request objects for creating and updating tasks 

exception → custom exceptions and global exception handler 

model → task model and enums 

service → business logic and task storage

TaskTrackerApplication → main Spring Boot application class

## Getting Started
1) Clone repository:
https://github.com/daria-markova/task-tracker.git

2) Open project in IntelliJ IDEA

3) Run the Spring Boot application

4) The API will be available at:
http://localhost:8080

## What I learned
- Building REST APIs with Spring Boot
- Working with HTTP methods and status codes
- Separating controller and service responsibilities
- Exception handling with @RestControllerAdvice
- Working with DTOs
- Input validation
- JSON data persistence with Jackson
- Working with collections
- Git workflow

## Future Improvements
- Add database persistence with PostgreSQL
- Add API documentation with Swagger / OpenAPI
- Add unit and integration tests
