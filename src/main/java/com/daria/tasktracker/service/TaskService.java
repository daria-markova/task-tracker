package com.daria.tasktracker.service;

import com.daria.tasktracker.exception.TaskNotFoundException;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.daria.tasktracker.model.Task;
import com.daria.tasktracker.model.enums.Priority;
import com.daria.tasktracker.model.enums.Status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;


@Service
public class TaskService {
    private int nextId = 1;
    private List<Task> tasks = new ArrayList<>();

    private final ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

    private final File file;

    public TaskService() {
        this.file = new File("tasks.json");
    }

    public TaskService(File file) {
        this.file = file;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public Task addTask(String title, Priority priority, LocalDate deadline) {
        Task task = new Task(nextId, title);
        task.setPriority(priority);
        task.setDeadline(deadline);

        tasks.add(task);
        saveTasks();
        nextId++;

        return task;
    }


    public Task startTask(int id) {
        Task task = findTaskById(id);

        if (task.getStatus() == Status.DONE) {
            return task;
        }

        task.setStatus(Status.IN_PROGRESS);
        saveTasks();
        return task;
    }

    public Task markDone(int id) {
        Task task = findTaskById(id);

        task.setStatus(Status.DONE);
        saveTasks();
        return task;
    }

    public Task deleteTask(int id) {
        Task task = findTaskById(id);

        tasks.remove(task);
        saveTasks();
        return task;
    }

    public Task findTaskById(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                return task;
            }
        }
        throw new TaskNotFoundException("Task not found");
    }
    
    public List<Task> showByStatus(Status status) {
        List<Task> result = new ArrayList<>();

        for (Task task : tasks) {
            if (task.getStatus() == status) {
                result.add(task);
            }
        }
        return result;
    }

    public List<Task> showByPriority(Priority priority) {
        List<Task> result = new ArrayList<>();

        for (Task task : tasks) {
            if (task.getPriority() == priority) {
                result.add(task);
            }
        }
        return result;
    }

    public List<Task> searchByTitle(String keyword) {
        List<Task> result = new ArrayList<>();
        String searchKeyword = keyword.toLowerCase();

        for (Task task : tasks) {
            if (task.getTitle().toLowerCase().contains(searchKeyword)) {
                result.add(task);
            }
        }
        return result;
    }


    public void saveTasks() {
        try {
            mapper.writeValue(file, tasks);
        } catch (IOException e) {
            System.out.println("Error while saving tasks: " + e.getMessage());
        }
    }

    @PostConstruct
    public void loadTasks() {
        try {
            if (file.exists()) {
                tasks = mapper.readValue(file, new TypeReference<List<Task>>() {});
                if (!tasks.isEmpty()) {
                    nextId = tasks.stream().mapToInt(Task::getId).max().orElse(0) + 1;
                }
            } else {
                tasks = new ArrayList<>();
            }
        } catch (IOException e) {
            System.out.println("Error while loading tasks: " + e.getMessage());
            tasks = new ArrayList<>();
        }
    }

    public Task editTask(int id, String newTitle, Priority newPriority) {

        Task task = findTaskById(id);

        task.setTitle(newTitle);
        task.setPriority(newPriority);

        saveTasks();

        return task;
    }

    public List<Task> showOverdueTasks() {
        List<Task> result = new ArrayList<>();
        LocalDate today = LocalDate.now();

        for (Task task : tasks) {
            if (task.getDeadline() != null && task.getDeadline().isBefore(today) && task.getStatus() != Status.DONE) {
                result.add(task);
            }
        }
        return result;
    }
}

