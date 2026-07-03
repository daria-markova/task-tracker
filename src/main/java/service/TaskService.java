package service;

import model.Task;
import model.enums.Priority;
import model.enums.Status;
import model.enums.Status;
import java.util.ArrayList;
import java.util.List;

public class TaskService {
    int nextId = 1;
    List<Task> tasks = new ArrayList<>();

    public void addTask(String title, Priority priority) {
        tasks.add(new Task(nextId, title));
        Task task = tasks.get(tasks.size() - 1);
        task.setPriority(priority);
        nextId++;
    }

    public void printTasks() {
        if (tasks.isEmpty()) {
            System.out.println("Task list is empty");
            return;
        }

        System.out.println("\n================ TASK LIST ================\n");

        for (Task task : tasks) {
            System.out.println("------------------------------------------");
            System.out.println("ID: " + task.getId());
            System.out.println("Title: " + task.getTitle());
            System.out.println("Status: " + formatStatus(task.getStatus()));
            System.out.println("Priority: " + task.getPriority());
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
                task.setStatus(Status.IN_PROGRESS);
                System.out.println("Task started");
                return;
            }
        }
        System.out.println("Task is not found");
    }

    public void markDone(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                task.setStatus(Status.DONE);
                System.out.println("Task marked as DONE");
                return;
            }
        }
        System.out.println("Task is not found");
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
            System.out.println("Task deleted");
        } else {
            System.out.println("Task is not found");
        }
    }

    private Task findTaskById(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null;
    }


}

