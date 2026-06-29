package main;

import java.util.ArrayList;
import java.util.List;

public class TaskService {
    int nextId = 1;
    List<Task> tasks = new ArrayList<>();

    public void addTask(String title) {
        tasks.add(new Task(nextId, title));
        nextId++;
    }

    public void printTasks() {
        if (tasks.isEmpty()) {
            System.out.println("Список задач пуст");
            return;
        }
        System.out.println("\n--- Задачи ---");

        for (Task task : tasks) {
            String status = task.isDone() ? "Выполнено ✓" : "В процессе";
            System.out.println(
                    "ID: " + task.getId() + " | " + task.getTitle() + " | " + status
            );
        }
        System.out.println("-------------\n");
    }

    public void markDone(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).markDone();
        }
    }

    public void deleteTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
        }
    }
}
