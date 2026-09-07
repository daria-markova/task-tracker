package com.daria.tasktracker.model;
import com.daria.tasktracker.model.enums.Priority;
import com.daria.tasktracker.model.enums.Status;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    private LocalDate deadline;

    public Task() {
    }

    public Task(int id, String title) {
        this.id = id;
        this.title = title;
        this.status = Status.TODO;
        this.priority = Priority.MEDIUM;
    }

    public Task(String title, Priority priority, LocalDate deadline) {
        this.title = title;
        this.status = Status.TODO;
        this.priority = priority;
        this.deadline = deadline;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;

    }

    public void setTitle(String title) {
        this.title = title;
    }
}
