package com.Academy.Task_Tool.Repository;

import java.util.Optional;

public interface TaskRepository {
    Optional<Object> findById(int taskId);
}
