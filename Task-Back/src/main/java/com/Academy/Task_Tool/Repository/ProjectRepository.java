package com.Academy.Task_Tool.Repository;

import com.Academy.Task_Tool.Entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Integer> {

     Optional<Project> findById(Integer project_Id);
}
