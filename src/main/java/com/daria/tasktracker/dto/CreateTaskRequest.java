package com.daria.tasktracker.dto;

import com.daria.tasktracker.model.enums.Priority;

import java.time.LocalDate;

public class CreateTaskRequest {

    private String title;
    private Priority priority;
    private LocalDate deadline;

    public String getTitle() {
        return title;
    }

    public Priority getPriority() {
        return priority;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }
}
