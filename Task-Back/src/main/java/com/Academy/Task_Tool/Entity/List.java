package com.Academy.Task_Tool.Entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @Column(name = "list_id")
    private Integer listId;

    @Column(name = "list_name")
    private String listName;

//    @Column(name = "position", nullable = false)
//    private Integer position;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;


    //Relations

    // relation with project
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @JsonBackReference
    @OneToMany(mappedBy = "list", cascade = CascadeType.ALL)
    private java.util.List<Card> cards ;

    //For soft delete
    @Column(name = "is_deleted")
    private Boolean isDeleted = false;

}
