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
            String status = task.isDone() ? "Выполнено" : "В процессе";
            System.out.println(
                    "ID: " + task.getId() + " | " + task.getTitle() + " | " + status
            );
        }
        System.out.println("-------------\n");
    }

    public void markDone(int id) {
        Task task = findTaskById(id);

        if (task != null) {
            task.markDone();
        }
    }

    public void deleteTask(int id) {
       Task task = findTaskById(id);

       if (task != null) {
           tasks.remove(task);
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
