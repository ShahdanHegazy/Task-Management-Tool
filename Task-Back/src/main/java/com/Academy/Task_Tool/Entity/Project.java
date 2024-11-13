package com.Academy.Task_Tool.Entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

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

    @Column(name = "project_name")
    private String projectName;

    @Column(name = "description")
    private String description;

    @Column(name = "start_date")
    private LocalDateTime start_date;

    @Column(name = "end_date")
    private LocalDateTime end_date;

    @Column(name="isDeleted")
    private Boolean isDeleted=false;

    @ManyToOne
    @JoinColumn(name = "project_manager_id", referencedColumnName = "id")
    private User projectManager;


    @ManyToMany(mappedBy = "assignedProjects")
    @JsonIgnore
    private Set<User> assignedUsers;

    @JsonBackReference
    @OneToMany(mappedBy="project")
    private Set<Card> assignedCards;

}

