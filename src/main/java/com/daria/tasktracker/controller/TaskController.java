package com.daria.tasktracker.controller;

import com.daria.tasktracker.dto.CreateTaskRequest;
import com.daria.tasktracker.model.Task;
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
    public Task getTaskById(@PathVariable int id) {
        return taskService.findTaskById(id);
    }

    @PostMapping("/tasks")
    public Task createTask(@RequestBody CreateTaskRequest request) {
        return taskService.addTask(request.getTitle(), request.getPriority(), request.getDeadline());
    }

}
