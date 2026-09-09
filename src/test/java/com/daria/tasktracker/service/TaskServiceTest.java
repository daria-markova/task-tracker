package com.daria.tasktracker.service;

import com.daria.tasktracker.exception.TaskNotFoundException;
import com.daria.tasktracker.model.Task;
import com.daria.tasktracker.model.enums.Priority;
import com.daria.tasktracker.model.enums.Status;
import com.daria.tasktracker.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TaskServiceTest {

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskRepository taskRepository;

    @BeforeEach
    void setUp() {
        taskRepository.deleteAll();
    }

    @Test
    void shouldCreateTask() {
        Task task = taskService.addTask("Test task", Priority.HIGH, LocalDate.of(2026, 9, 10));

        assertEquals("Test task", task.getTitle());
        assertEquals(Priority.HIGH, task.getPriority());
        assertEquals(LocalDate.of(2026, 9, 10), task.getDeadline());
        assertEquals(Status.TODO, task.getStatus());
        assertTrue(task.getId() > 0);
    }

    @Test
    void shouldFindTaskById() {
        Task createdTask = taskService.addTask("Find me", Priority.MEDIUM, LocalDate.of(2026, 9, 10));

        Task foundTask = taskService.findTaskById(createdTask.getId());

        assertEquals(createdTask.getId(), foundTask.getId());
        assertEquals("Find me", foundTask.getTitle());
    }

    @Test
    void shouldThrowExceptionWhenTaskNotFound() {
        assertThrows(TaskNotFoundException.class, () -> taskService.findTaskById(999));
    }

    @Test
    void shouldMarkTaskAsDone() {
        Task task = taskService.addTask("Complete me", Priority.HIGH, LocalDate.of(2026, 9, 10));

        taskService.markDone(task.getId());

        assertEquals(Status.DONE, taskService.findTaskById(task.getId()).getStatus());
    }

    @Test
    void shouldStartTask() {
        Task task = taskService.addTask("Start me", Priority.MEDIUM, LocalDate.of(2026, 9, 10));

        taskService.startTask(task.getId());

        assertEquals(Status.IN_PROGRESS, taskService.findTaskById(task.getId()).getStatus());
    }

    @Test
    void shouldNotStartCompletedTask() {
        Task task = taskService.addTask("Completed task", Priority.HIGH, LocalDate.of(2026, 9, 10));

        taskService.markDone(task.getId());
        taskService.startTask(task.getId());

        assertEquals(Status.DONE, taskService.findTaskById(task.getId()).getStatus());
    }

    @Test
    void shouldDeleteTask() {
        Task task = taskService.addTask("Delete me", Priority.LOW, LocalDate.of(2026, 9, 10));

        taskService.deleteTask(task.getId());

        assertThrows(TaskNotFoundException.class, () -> taskService.findTaskById(task.getId()));
    }

    @Test
    void shouldEditTask() {
        Task task = taskService.addTask("Old title", Priority.LOW, LocalDate.of(2026, 9, 10));

        taskService.editTask(task.getId(), "New title", Priority.HIGH);

        Task updatedTask = taskService.findTaskById(task.getId());

        assertEquals("New title", updatedTask.getTitle());
        assertEquals(Priority.HIGH, updatedTask.getPriority());
    }

    @Test
    void shouldThrowExceptionWhenEditingTaskNotFound() {
        assertThrows(TaskNotFoundException.class, () -> taskService.editTask(999, "New title", Priority.HIGH));
    }

    @Test
    void shouldFindTasksByTitle() {
        taskService.addTask("Buy milk", Priority.MEDIUM, LocalDate.of(2026, 9, 10));

        taskService.addTask("Buy sweets", Priority.LOW, LocalDate.of(2026, 9, 15));

        List<Task> result = taskService.searchByTitle("buy");

        assertEquals(2, result.size());
    }

    @Test
    void shouldFindTasksByStatus() {
        Task firstTask = taskService.addTask("First task", Priority.MEDIUM, LocalDate.of(2026, 9, 10));

        taskService.addTask("Second task", Priority.LOW, LocalDate.of(2026, 9, 15));

        taskService.markDone(firstTask.getId());

        List<Task> result = taskService.showByStatus(Status.DONE);

        assertEquals(1, result.size());
        assertEquals(firstTask.getId(), result.get(0).getId());
    }

    @Test
    void shouldFindTasksByPriority() {
        Task firstTask = taskService.addTask("Important task", Priority.HIGH, LocalDate.of(2026, 9, 10));

        taskService.addTask("Normal task", Priority.LOW, LocalDate.of(2026, 9, 15));

        List<Task> result = taskService.showByPriority(Priority.HIGH);

        assertEquals(1, result.size());
        assertEquals(firstTask.getId(), result.get(0).getId());
    }

    @Test
    void shouldFindOverdueTasks() {
        Task overdueTask = taskService.addTask("Overdue task", Priority.HIGH, LocalDate.of(2026, 9, 3));

        taskService.addTask("Future task", Priority.LOW, LocalDate.of(2026, 9, 10));

        Task completedTask = taskService.addTask("Completed task", Priority.MEDIUM, LocalDate.of(2026, 9, 2));

        taskService.markDone(completedTask.getId());

        List<Task> result = taskService.showOverdueTasks();

        assertEquals(1, result.size());
        assertEquals(overdueTask.getId(), result.get(0).getId());
    }
}