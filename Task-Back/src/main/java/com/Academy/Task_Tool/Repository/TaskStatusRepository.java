package com.Academy.Task_Tool.Repository;

import com.Academy.Task_Tool.Entity.Task_status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskStatusRepository extends JpaRepository<Task_status,Integer> {
}
