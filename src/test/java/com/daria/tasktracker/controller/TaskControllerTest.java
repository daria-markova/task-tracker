package com.daria.tasktracker.controller;

import com.daria.tasktracker.exception.TaskNotFoundException;
import com.daria.tasktracker.model.Task;
import com.daria.tasktracker.model.enums.Priority;
import com.daria.tasktracker.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TaskController.class)
public class TaskControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    @Test
    void shouldCreateTask() throws Exception {
        Task task = new Task(1, "Test task");
        task.setPriority(Priority.HIGH);
        task.setDeadline(LocalDate.of(2026, 9, 10));

        when(taskService.addTask("Test task", Priority.HIGH, LocalDate.of(2026, 9, 10))).thenReturn(task);

        mockMvc.perform(post("/tasks").contentType(MediaType.APPLICATION_JSON).content("""
                {
                  "title": "Test task",
                  "priority": "HIGH",
                  "deadline": "2026-09-10"
                }
                """)).andExpect(status().isCreated());

    }

        @Test
        void shouldRejectTaskWithBlankTitle() throws Exception {
            mockMvc.perform(post("/tasks").contentType(MediaType.APPLICATION_JSON).content("""
                    {
                      "title": "",
                      "priority": "HIGH",
                      "deadline": "2026-09-10"
                    }
                    """)).andExpect(status().isBadRequest());
        }

        @Test
        void shouldRejectTaskWithoutPriority() throws Exception {
            mockMvc.perform(post("/tasks").contentType(MediaType.APPLICATION_JSON).content("""
                    {
                      "title": "Test task",
                      "deadline": "2026-09-10"
                    }
                    """)).andExpect(status().isBadRequest());
        }

    @Test
    void shouldGetTaskById() throws Exception {
        Task task = new Task(1, "Test task");
        task.setPriority(Priority.HIGH);

        when(taskService.findTaskById(1)).thenReturn(task);

        mockMvc.perform(get("/tasks/1")).andExpect(status().isOk());
    }

    @Test
    void shouldReturnNotFoundWhenTaskDoesNotExist() throws Exception {
        when(taskService.findTaskById(999)).thenThrow(new TaskNotFoundException("Task not found"));

        mockMvc.perform(get("/tasks/999")).andExpect(status().isNotFound());
    }

    @Test
    void shouldGetAllTasks() throws Exception {
        Task task = new Task(1, "Test task");

        when(taskService.getTasks()).thenReturn(List.of(task));

        mockMvc.perform(get("/tasks")).andExpect(status().isOk());
    }

    @Test
    void shouldDeleteTask() throws Exception {
        Task task = new Task(1, "Test task");

        when(taskService.deleteTask(1)).thenReturn(task);

        mockMvc.perform(delete("/tasks/1")).andExpect(status().isNoContent());
    }

    @Test
    void shouldUpdateTask() throws Exception {
        Task task = new Task(1, "Updated task");
        task.setPriority(Priority.HIGH);

        when(taskService.editTask(1, "Updated task", Priority.HIGH)).thenReturn(task);

        mockMvc.perform(put("/tasks/1").contentType(MediaType.APPLICATION_JSON).content("""
                    {
                      "title": "Updated task",
                      "priority": "HIGH"
                    }
                    """)).andExpect(status().isOk());
    }

    @Test
    void shouldStartTask() throws Exception {
        Task task = new Task(1, "Test task");

        when(taskService.startTask(1)).thenReturn(task);

        mockMvc.perform(put("/tasks/1/start")).andExpect(status().isOk());
    }

    @Test
    void shouldCompleteTask() throws Exception {
        Task task = new Task(1, "Test task");

        when(taskService.markDone(1)).thenReturn(task);

        mockMvc.perform(put("/tasks/1/complete")).andExpect(status().isOk());
    }

    @Test
    void shouldReturnConflictWhenStartingCompletedTask() throws Exception {
        Task task = new Task(1, "Test task");
        task.setStatus(com.daria.tasktracker.model.enums.Status.DONE);

        when(taskService.startTask(1)).thenReturn(task);

        mockMvc.perform(put("/tasks/1/start")).andExpect(status().isConflict());
    }

    @Test
    void shouldRejectUpdateWithoutPriority() throws Exception {
        mockMvc.perform(put("/tasks/1").contentType(MediaType.APPLICATION_JSON).content("""
                    {
                      "title": "Updated task"
                    }
                    """)).andExpect(status().isBadRequest());
    }

}