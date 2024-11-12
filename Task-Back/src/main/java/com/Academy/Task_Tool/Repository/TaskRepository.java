package com.Academy.Task_Tool.Repository;

import com.Academy.Task_Tool.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task,Integer>{
    Optional<Object> findById(int taskId);



}
