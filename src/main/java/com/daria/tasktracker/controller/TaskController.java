package com.daria.tasktracker.controller;

import com.daria.tasktracker.dto.CreateTaskRequest;
import com.daria.tasktracker.dto.UpdateTaskRequest;
import com.daria.tasktracker.model.Task;
import com.daria.tasktracker.model.enums.Priority;
import com.daria.tasktracker.model.enums.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.daria.tasktracker.service.TaskService;

import java.util.List;

@RestController
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @GetMapping("/tasks")
    public List<Task> getTasks() {
        return taskService.getTasks();
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable int id) {

        Task task = taskService.findTaskById(id);

        if (task == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(task);
    }

    @GetMapping("/tasks/status/{status}")
    public List<Task> getTasksByStatus(@PathVariable Status status) {
        return taskService.showByStatus(status);
    }

    @GetMapping("/tasks/priority/{priority}")
    public List<Task> getTasksByPriority(@PathVariable Priority priority) {
        return taskService.showByPriority(priority);
    }

    @GetMapping("/tasks/search")
    public List<Task> searchTasks(@RequestParam String keyword) {
        return taskService.searchByTitle(keyword);
    }

    @GetMapping("/tasks/overdue")
    public List<Task> getOverdueTasks() {
        return taskService.showOverdueTasks();
    }

        @PostMapping("/tasks")
    public ResponseEntity<Task> createTask(@RequestBody CreateTaskRequest request) {

        Task task = taskService.addTask(request.getTitle(), request.getPriority(), request.getDeadline()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(task);
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable int id) {
        boolean deleted = taskService.deleteTask(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable int id, @RequestBody UpdateTaskRequest request) {
        Task task = taskService.editTask(id, request.getTitle(), request.getPriority());

        if (task == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task);
    }

    @PutMapping("/tasks/{id}/complete")
    public void completeTask(@PathVariable int id) {
        taskService.markDone(id);
    }

    @PutMapping("/tasks/{id}/start")
    public ResponseEntity<Void> startTask(@PathVariable int id) {
        Task task = taskService.startTask(id);

        if (task == null) {
            return ResponseEntity.notFound().build();
        }

        if (task.getStatus() == Status.DONE) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        return ResponseEntity.ok().build();
    }

}
