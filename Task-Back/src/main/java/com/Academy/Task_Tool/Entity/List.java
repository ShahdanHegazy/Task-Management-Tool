package com.Academy.Task_Tool.Entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Set;


@Table(name = "lists")
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class List {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "status_id")
    private Integer status_id;

    @Column(name = "status_name")
    private String status_name;

    @Column(name = "position", nullable = false)
    private Integer position;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;


    //Relations

    // relation with project
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @OneToMany(mappedBy = "list")
    private Set<Card> cards;

    //For soft delete
    @Column(name = "is_deleted")
    private Boolean isDeleted = false;

}
