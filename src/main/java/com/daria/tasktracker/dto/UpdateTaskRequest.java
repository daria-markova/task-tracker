package com.daria.tasktracker.dto;

import com.daria.tasktracker.model.enums.Priority;

public class UpdateTaskRequest {
    private String title;
    private Priority priority;

    public String getTitle() {
        return title;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }
}
