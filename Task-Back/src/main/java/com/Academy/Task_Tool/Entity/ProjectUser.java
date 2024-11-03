package com.Academy.Task_Tool.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="project_user")
public class ProjectUser {

    @Id
    @Column(name = "pro_us_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer proUsId;

    @Column(name="project_id")
    private Integer projectId;

    @Column(name="user_id")
    private Integer userId;

    @Column(name = "role_in_project")
    private String roleInProject;
}
