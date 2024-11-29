package com.Academy.Task_Tool.Entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import com.Academy.Task_Tool.Entity.List;


import java.time.LocalDate;
import java.util.ArrayList;

import java.time.LocalDate;
import java.util.Set;

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
    private String projectName;

    @Column(name = "description")
    private String description;

    @Column(name = "start_date")
    private LocalDate start_date;

    @Column(name = "end_date")
    private LocalDate end_date;

    @Column(name="isDeleted")
    private Boolean isDeleted=false;
  
    //Relations

    // relation with user for project manager
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "project_manager_id", referencedColumnName = "id")
    private User projectManager;

    //relation with users for users

    @ManyToMany(mappedBy = "assignedProjects")
    @JsonIgnore
    private java.util.List<User> assignedUsers = new ArrayList<>();

    //relation with cards
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private java.util.List<List> lists ;


 
}

