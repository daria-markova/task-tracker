package com.daria.tasktracker.repository;

import com.daria.tasktracker.model.Task;
import com.daria.tasktracker.model.enums.Priority;
import com.daria.tasktracker.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findByStatus(Status status);

    List<Task> findByPriority(Priority priority);

    List<Task> findByTitleContainingIgnoreCase(String keyword);
}
