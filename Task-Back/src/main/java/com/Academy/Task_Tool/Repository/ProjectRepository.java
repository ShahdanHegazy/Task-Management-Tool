package com.Academy.Task_Tool.Repository;

import com.Academy.Task_Tool.Entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project,Integer> {
}
