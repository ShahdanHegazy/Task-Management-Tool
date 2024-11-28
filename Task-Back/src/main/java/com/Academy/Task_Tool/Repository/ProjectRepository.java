package com.Academy.Task_Tool.Repository;

import com.Academy.Task_Tool.DTO.UserDto;
import com.Academy.Task_Tool.Entity.Project;
import com.Academy.Task_Tool.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

//     Custom query to count all project
    @Query("SELECT COUNT(u.project_id) FROM Project u WHERE u.isDeleted = false ")
    Integer countAllProject();

    @Query("SELECT u FROM Project u WHERE u.isDeleted = false ")
    List<Project> findAllActiveProjects();

    @Query("SELECT u FROM User u JOIN u.assignedProjects p WHERE p.project_id = :projectId AND p.isDeleted = false")
    List<User> findAllUsersByProjectId(Integer projectId);

    @Query("SELECT p FROM Project p WHERE p.projectManager.id = :userId AND p.isDeleted = false")
    List<Project> findManagedProjectsByUserId(@Param("userId") int userId);

    @Query("SELECT p FROM Project p JOIN p.assignedUsers u WHERE u.id = :userId AND p.isDeleted = false")
    List<Project> findAssignedProjectsByUserId(@Param("userId") int userId);



}
