package com.Academy.Task_Tool.Repository;

import com.Academy.Task_Tool.Entity.Project;
import com.Academy.Task_Tool.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

//     Custom query to count all project
    @Query("SELECT COUNT(u.project_id) FROM Project u")
    Integer countAllProject();

    @Query("SELECT u FROM Project u WHERE u.isDeleted = false ")
    List<Project> findAllActiveProjects();

}
