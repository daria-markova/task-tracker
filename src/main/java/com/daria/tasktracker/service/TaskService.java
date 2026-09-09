package com.daria.tasktracker.service;

import com.daria.tasktracker.exception.TaskNotFoundException;
import com.daria.tasktracker.model.TaskHistory;
import com.daria.tasktracker.repository.TaskHistoryRepository;
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
    private final TaskHistoryRepository taskHistoryRepository;

    public TaskService(TaskRepository taskRepository, TaskHistoryRepository taskHistoryRepository) {
        this.taskRepository = taskRepository;
        this.taskHistoryRepository = taskHistoryRepository;
    }

    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    public Task addTask(String title, Priority priority, LocalDate deadline) {
        Task task = new Task(title, priority, deadline);
        Task savedTask = taskRepository.save(task);

        TaskHistory history = new TaskHistory("CREATED");
        history.setTask(savedTask);
        taskHistoryRepository.save(history);

        return savedTask;
    }

    public Task startTask(int id) {
        Task task = findTaskById(id);

        if (task.getStatus() != Status.TODO) {
            return task;
        }

        task.setStatus(Status.IN_PROGRESS);
        Task savedTask = taskRepository.save(task);

        TaskHistory history = new TaskHistory("STARTED");
        history.setTask(savedTask);
        taskHistoryRepository.save(history);

        return savedTask;
    }

    public Task markDone(int id) {
        Task task = findTaskById(id);

        task.setStatus(Status.DONE);
        Task savedTask = taskRepository.save(task);

        TaskHistory history = new TaskHistory("COMPLETED");
        history.setTask(savedTask);
        taskHistoryRepository.save(history);

        return savedTask;
    }

    public void deleteTask(int id) {
        Task task = findTaskById(id);
        taskRepository.delete(task);
    }

    public Task findTaskById(int id) {
        return taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("Task not found"));
    }

    public List<Task> showByStatus(Status status) {
        return taskRepository.findByStatus(status);
    }


    public List<Task> showByPriority(Priority priority) {
        return taskRepository.findByPriority(priority);
    }

    public List<Task> searchByTitle(String keyword) {
        return taskRepository.findByTitleContainingIgnoreCase(keyword);
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

    public List<TaskHistory> getTaskHistory(int taskId) {
        findTaskById(taskId);

        return taskHistoryRepository.findByTaskId(taskId);
    }

}

