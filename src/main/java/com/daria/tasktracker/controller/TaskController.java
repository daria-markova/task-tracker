package com.daria.tasktracker.controller;

import com.daria.tasktracker.dto.CreateTaskRequest;
import com.daria.tasktracker.dto.UpdateTaskRequest;
import com.daria.tasktracker.model.Task;
import com.daria.tasktracker.model.TaskHistory;
import com.daria.tasktracker.model.enums.Priority;
import com.daria.tasktracker.model.enums.Status;
import jakarta.validation.Valid;
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

    @GetMapping("/tasks/{id}/history")
    public List<TaskHistory> getTaskHistory(@PathVariable int id) {
        return taskService.getTaskHistory(id);
    }

    @PostMapping("/tasks")
    public ResponseEntity<Task> createTask(@Valid @RequestBody CreateTaskRequest request) {

        Task task = taskService.addTask(request.getTitle(), request.getPriority(), request.getDeadline());
        return ResponseEntity.status(HttpStatus.CREATED).body(task);
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable int id) {
        taskService.deleteTask(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable int id, @Valid @RequestBody UpdateTaskRequest request) {
        Task task = taskService.editTask(id, request.getTitle(), request.getPriority());

        return ResponseEntity.ok(task);
    }

    @PutMapping("/tasks/{id}/complete")
    public ResponseEntity<Task> completeTask(@PathVariable int id) {
        Task task = taskService.markDone(id);

        return ResponseEntity.ok(task);
    }

    @PutMapping("/tasks/{id}/start")
    public ResponseEntity<Void> startTask(@PathVariable int id) {
        Task task = taskService.startTask(id);


        if (task.getStatus() == Status.DONE) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        return ResponseEntity.ok().build();
    }

}
