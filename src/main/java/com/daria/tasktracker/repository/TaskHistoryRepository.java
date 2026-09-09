package com.daria.tasktracker.repository;

import com.daria.tasktracker.model.TaskHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskHistoryRepository extends JpaRepository<TaskHistory, Integer> {
    List<TaskHistory> findByTaskId(int taskId);
}
