package com.Academy.Task_Tool.Repository;

import com.Academy.Task_Tool.Entity.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskStatusRepository extends JpaRepository<TaskStatus,Integer> {
}
