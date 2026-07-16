package com.daria.tasktracker.service;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.daria.tasktracker.model.Task;
import com.daria.tasktracker.model.enums.Priority;
import com.daria.tasktracker.model.enums.Status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;


@Service
public class TaskService {
    int nextId = 1;
    List<Task> tasks = new ArrayList<>();

    public List<Task> getTasks() {
        return tasks;
    }

    public Task addTask(String title, Priority priority, LocalDate deadline) {
        tasks.add(new Task(nextId, title));

        Task task = tasks.get(tasks.size() - 1);
        task.setPriority(priority);
        task.setDeadline(deadline);
        saveTasks();
        nextId++;

        return task;

    }

    public void printTasks() {
        if (tasks.isEmpty()) {
            System.out.println("Task list is empty");
            return;
        }

        System.out.println("\n================ TASK LIST ================\n");

        for (Task task : tasks) {
            printTask(task);
        }

        System.out.println("------------------------------------------\n");
    }

    private String formatStatus(Status status) {
        return switch (status) {
            case TODO -> "TO DO";
            case IN_PROGRESS -> "IN PROGRESS";
            case DONE -> "DONE";
        };
    }

    public void startTask(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {

                if (task.getStatus() == Status.DONE) {
                    System.out.println("Task is already completed");
                    return;
                }

                task.setStatus(Status.IN_PROGRESS);
                saveTasks();
                System.out.println("Task started");
                return;
            }

        }

        System.out.println("Task not found");
    }

    public void markDone(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                task.setStatus(Status.DONE);
                saveTasks();
                System.out.println("Task marked as DONE");
                return;
            }
        }
        System.out.println("Task not found");
    }

    public void deleteTask(int id) {
        Task taskToDelete = null;

        for (Task task : tasks) {
            if (task.getId() == id) {
                taskToDelete = task;
                break;
            }
        }

        if (taskToDelete != null) {
            tasks.remove(taskToDelete);
            saveTasks();
            System.out.println("Task deleted");
        } else {
            System.out.println("Task not found");
        }
    }

    public Task findTaskById(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null;
    }
    public void showByStatus(Status status) {
        for (Task task : tasks) {
            if (task.getStatus() == status) {
                printTask(task);
            }
        }
    }

    public void showByPriority(Priority priority) {
        for (Task task : tasks) {
            if (task.getPriority() == priority) {
                printTask(task);
            }
        }
    }

    private void printTask(Task task) {
        System.out.println("------------------------------------------");
        System.out.println("ID: " + task.getId());
        System.out.println("Title: " + task.getTitle());
        System.out.println("Status: " + formatStatus(task.getStatus()));
        System.out.println("Priority: " + task.getPriority());
        if (task.getDeadline() != null) {
            System.out.println("Deadline: " + task.getDeadline());
        } else {
            System.out.println("Deadline: not set");
        }
    }

    public void searchByTitle(String keyword) {
        boolean found = false;

        for (Task task : tasks) {
            if (task.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                printTask(task);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No tasks found for: " + keyword);
        }
    }

    private final ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
    private final File file = new File("tasks.json");

    public void saveTasks() {
        try {
            mapper.writeValue(file, tasks);
        } catch (IOException e) {
            System.out.println("Error while saving tasks: " + e.getMessage());
        }
    }

    public void loadTasks() {
        try {
            if (file.exists()) {
                tasks = mapper.readValue(file, new TypeReference<List<Task>>() {});
            } else {
                tasks = new ArrayList<>();
            }
        } catch (IOException e) {
            System.out.println("Error while loading tasks: " + e.getMessage());
            tasks = new ArrayList<>();
        }
    }

    public void editTask(int id, String newTitle, Priority newPriority) {

        Task task = findTaskById(id);

        if (task == null) {
            System.out.println("Task not found");
            return;
        }

        task.setTitle(newTitle);
        task.setPriority(newPriority);

        saveTasks();

        System.out.println("Task updated");
    }

    public void showOverdueTasks() {
        for (Task task : tasks) {
            if (task.getDeadline() != null && task.getDeadline().isBefore(LocalDate.now()) && task.getStatus() != Status.DONE) {
                printTask(task);
            }
        }
    }
}

