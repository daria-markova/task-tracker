package com.daria.tasktracker.dto;

import com.daria.tasktracker.model.enums.Priority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UpdateTaskRequest {
    @NotBlank(message = "Title must not be blank")
    private String title;

    @NotNull(message = "Priority must not be null")
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
