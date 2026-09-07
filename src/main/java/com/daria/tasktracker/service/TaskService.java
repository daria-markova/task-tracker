package com.daria.tasktracker.service;

import com.daria.tasktracker.exception.TaskNotFoundException;
import com.daria.tasktracker.repository.TaskRepository;
import com.daria.tasktracker.model.Task;
import com.daria.tasktracker.model.enums.Priority;
import com.daria.tasktracker.model.enums.Status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;


@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    public Task addTask(String title, Priority priority, LocalDate deadline) {
        Task task = new Task(title, priority, deadline);

        return taskRepository.save(task);
    }


    public Task startTask(int id) {
        Task task = findTaskById(id);

        if (task.getStatus() == Status.DONE) {
            return task;
        }

        task.setStatus(Status.IN_PROGRESS);
        return taskRepository.save(task);
    }

    public Task markDone(int id) {
        Task task = findTaskById(id);

        task.setStatus(Status.DONE);
        return taskRepository.save(task);
    }

    public Task deleteTask(int id) {
        Task task = findTaskById(id);

        taskRepository.delete(task);
        return task;
    }

    public Task findTaskById(int id) {
        return taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("Task not found"));
    }

    public List<Task> showByStatus(Status status) {
        List<Task> result = new ArrayList<>();

        for (Task task : taskRepository.findAll()) {
            if (task.getStatus() == status) {
                result.add(task);
            }
        }
        return result;
    }


    public List<Task> showByPriority(Priority priority) {
        List<Task> result = new ArrayList<>();

        for (Task task : taskRepository.findAll()) {
            if (task.getPriority() == priority) {
                result.add(task);
            }
        }
        return result;
    }

    public List<Task> searchByTitle(String keyword) {
        List<Task> result = new ArrayList<>();
        String searchKeyword = keyword.toLowerCase();

        for (Task task : taskRepository.findAll()) {
            if (task.getTitle().toLowerCase().contains(searchKeyword)) {
                result.add(task);
            }
        }
        return result;
    }


    public Task editTask(int id, String newTitle, Priority newPriority) {
        Task task = findTaskById(id);

        task.setTitle(newTitle);
        task.setPriority(newPriority);

        return taskRepository.save(task);
    }

    public List<Task> showOverdueTasks() {
        List<Task> result = new ArrayList<>();
        LocalDate today = LocalDate.now();

        for (Task task : taskRepository.findAll()) {
            if (task.getDeadline() != null && task.getDeadline().isBefore(today) && task.getStatus() != Status.DONE) {result.add(task);
            }
        }
        return result;
    }

}

