package com.Academy.Task_Tool.Entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Table(name = "project")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Integer project_id;

    @Column(name = "Project_Name")
    private String projectName;

    @Column(name = "description")
    private String description;

    @Column(name = "start_date")
    private LocalDateTime start_date;

    @Column(name = "end_date")
    private LocalDateTime end_date;

//    @Column(name = "status")
//    private String status;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "project_manager_id", referencedColumnName = "id")
    private User projectManager;

    @ManyToMany(mappedBy = "assignedProjects")
    private Set<User> assignedUsers;

    @JsonBackReference
    @OneToMany(mappedBy="project")
    private Set<Task> assignedTasks;

}

