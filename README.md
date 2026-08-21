# Task Tracker (Java Spring Boot REST API)

A task management REST API that allows users to create, update, delete, and manage tasks. The project demonstrates object-oriented programming principles, REST API development, exception handling, and backend development fundamentals.

## Features
Create new tasks
Get all tasks
Get a task by ID
Update tasks
Start tasks
Mark tasks as done
Delete tasks
Search tasks by title
Filter tasks by:
Status (TODO/IN_PROGRESS/DONE)
Priority (LOW/MEDIUM/HIGH)
Get overdue tasks
Persistent task storage in JSON
Automatic task ID generation
Handle errors with custom exceptions

## Tech Stack
Java 21
Spring Boot
Spring Web
Maven
Jackson
OOP (Encapsulation, Abstraction)
Collections (ArrayList)
REST API
JSON
Git / GitHub

## Project Structure

App — contains the main class for the console version (Main)

controller — REST API endpoints (TaskController)

dto — request objects for creating and updating tasks (CreateTaskRequest, UpdateTaskRequest)

exception — custom exceptions and global exception handler (TaskNotFoundException, GlobalExceptionHandler)

model — task model and enums (Task, Priority, Status)

service — business logic and file storage (TaskService, FileStorage)

TaskTrackerApplication — main Spring Boot application class

## Getting Started
Clone repository:
https://github.com/daria-markova/task-tracker.git

Open project in IntelliJ IDEA
Run the Spring Boot application
The API will be available at:
http://localhost:8080

## What I learned
Building REST APIs with Spring Boot
Working with HTTP methods and status codes
Separating controller and service responsibilities
Exception handling with @RestControllerAdvice
Working with DTOs
JSON data persistence with Jackson
Working with collections
Git workflow

## Future Improvements
Add database persistence (PostgreSQL)
Add input validation
Add API documentation with Swagger / OpenAPI
Add unit and integration tests
Improve API documentation
