package com.Academy.Task_Tool.Repository;

import com.Academy.Task_Tool.Entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Integer> {

     Optional<Project> findById(Integer project_Id);

//     Custom query to count all project
    @Query("SELECT COUNT(u.project_id) FROM Project u")
    Integer countAllProject();

}
