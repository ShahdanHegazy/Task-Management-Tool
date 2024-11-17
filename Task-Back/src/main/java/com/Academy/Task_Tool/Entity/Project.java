package com.Academy.Task_Tool.Entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import java.time.LocalDateTime;

@Table(name = "project")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Integer project_id;

    @Column(name = "project_title")
    private String projectTitle;

    @Column(name = "description")
    private String description;

    @Column(name = "start_date")
    private LocalDateTime start_date;

    @Column(name = "end_date")
    private LocalDateTime end_date;

    //Relations

    // relation with user for project manager
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "project_manager_id", referencedColumnName = "id")
    private User projectManager;

    //relation with users for memebers
    @ManyToMany(mappedBy = "assignedProjects")
    private Set<User> assignedUsers;

    //relation with cards
    @JsonBackReference
    @OneToMany(mappedBy="project")
    private Set<Card> assignedCards;

    //For soft delete

    @Column(name = "is_deleted")
    private Boolean isDeleted = false;



}

