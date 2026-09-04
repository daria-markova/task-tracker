package com.daria.tasktracker.service;

import com.daria.tasktracker.exception.TaskNotFoundException;
import com.daria.tasktracker.model.Task;
import com.daria.tasktracker.model.enums.Priority;
import com.daria.tasktracker.model.enums.Status;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TaskServiceTest {
    @Test
    void shouldCreateTask() {
        TaskService taskService = new TaskService();

        Task task = taskService.addTask("Test task", Priority.HIGH, LocalDate.of(2026, 9, 10));

        assertEquals("Test task", task.getTitle());
        assertEquals(Priority.HIGH, task.getPriority());
        assertEquals(LocalDate.of(2026, 9, 10), task.getDeadline());
        assertEquals(1, task.getId());

        Task secondTask = taskService.addTask("Second test task", Priority.LOW, LocalDate.of(2026, 9, 15));

        assertEquals(2, secondTask.getId());
    }

    @Test
    void shouldFindTaskById() {
        TaskService taskService = new TaskService();

        Task createdTask = taskService.addTask("Find me", Priority.MEDIUM, LocalDate.of(2026, 9, 10));
        Task foundTask = taskService.findTaskById(createdTask.getId());

        assertEquals(createdTask.getId(), foundTask.getId());
        assertEquals("Find me", foundTask.getTitle());
    }

    @Test
    void shouldThrowExceptionWhenTaskNotFound() {
        TaskService taskService = new TaskService();

        assertThrows(TaskNotFoundException.class, () -> taskService.findTaskById(999));
    }

    @Test
    void shouldMarkTaskAsDone() {
        TaskService taskService = new TaskService();

        Task task = taskService.addTask("Complete me", Priority.HIGH, LocalDate.of(2026, 9, 10));
        taskService.markDone(task.getId());

        assertEquals(Status.DONE, task.getStatus());
    }

    @Test
    void shouldStartTask() {
        TaskService taskService = new TaskService();

        Task task = taskService.addTask("Start me", Priority.MEDIUM, LocalDate.of(2026, 9, 10));
        taskService.startTask(task.getId());

        assertEquals(Status.IN_PROGRESS, task.getStatus());
    }

    @Test
    void shouldNotStartCompletedTask() {
        TaskService taskService = new TaskService();

        Task task = taskService.addTask("Completed task", Priority.HIGH, LocalDate.of(2026, 9, 10));

        taskService.markDone(task.getId());
        taskService.startTask(task.getId());

        assertEquals(Status.DONE, task.getStatus());
    }

    @Test
    void shouldDeleteTask() {
        TaskService taskService = new TaskService();

        Task task = taskService.addTask("Delete me", Priority.LOW, LocalDate.of(2026, 9, 10));

        taskService.deleteTask(task.getId());

        assertThrows(TaskNotFoundException.class, () -> taskService.findTaskById(task.getId()));
    }

    @Test
    void shouldEditTask() {
        TaskService taskService = new TaskService();

        Task task = taskService.addTask("Old title", Priority.LOW, LocalDate.of(2026, 9, 10));

        taskService.editTask(task.getId(), "New title", Priority.HIGH);

        assertEquals("New title", task.getTitle());
        assertEquals(Priority.HIGH, task.getPriority());
    }

    @Test
    void shouldFindTasksByTitle() {
        TaskService taskService = new TaskService();

        taskService.addTask("Buy milk", Priority.MEDIUM, LocalDate.of(2026, 9, 10));

        taskService.addTask("Buy sweets", Priority.LOW, LocalDate.of(2026, 9, 15));

        List<Task> result = taskService.searchByTitle("buy");

        assertEquals(2, result.size());
    }

    @Test
    void shouldFindTasksByStatus() {
        TaskService taskService = new TaskService();

        Task firstTask = taskService.addTask("First task", Priority.MEDIUM, LocalDate.of(2026, 9, 10));

        taskService.addTask("Second task", Priority.LOW, LocalDate.of(2026, 9, 15));

        taskService.markDone(firstTask.getId());

        List<Task> result = taskService.showByStatus(Status.DONE);

        assertEquals(1, result.size());
        assertEquals(firstTask.getId(), result.get(0).getId());
    }

    @Test
    void shouldFindTasksByPriority() {
        TaskService taskService = new TaskService();

        Task firstTask = taskService.addTask("Important task", Priority.HIGH, LocalDate.of(2026, 9, 10));

        taskService.addTask("Normal task", Priority.LOW, LocalDate.of(2026, 9, 15));

        List<Task> result = taskService.showByPriority(Priority.HIGH);

        assertEquals(1, result.size());
        assertEquals(firstTask.getId(), result.get(0).getId());
    }

    @Test
    void shouldFindOverdueTasks() {
        TaskService taskService = new TaskService();

        Task overdueTask = taskService.addTask("Overdue task", Priority.HIGH, LocalDate.of(2026, 9, 3));

        taskService.addTask("Future task", Priority.LOW, LocalDate.of(2026, 9, 10));

        Task completedTask = taskService.addTask("Completed task", Priority.MEDIUM, LocalDate.of(2026, 9, 2));

        taskService.markDone(completedTask.getId());

        List<Task> result = taskService.showOverdueTasks();

        assertEquals(1, result.size());
        assertEquals(overdueTask.getId(), result.get(0).getId());
    }
}
