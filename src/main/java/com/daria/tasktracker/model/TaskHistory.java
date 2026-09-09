package com.daria.tasktracker.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class TaskHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String action;

    private LocalDateTime createdAt;


    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    public TaskHistory() {
    }

    public TaskHistory(String action) {
        this.action = action;
        this.createdAt = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public String getAction() {
        return action;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
