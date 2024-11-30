package com.Academy.Task_Tool.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name="Cards")
public class Card {
    @Id
    @Column(name = "card_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cardId;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(name = "priority")
    private String priority;

    @Column(name = "create_at")
    private LocalDate createAt;

    @Column(name = "update_at")
    private LocalDate updateAt;


    //Relations

    //relation one to many with user table
    @ManyToOne
    @JoinColumn(name = "assigned_to_user_id")
    private User assignedTo;

    // relation one to one with user
    @ManyToOne
    @JoinColumn(name = "created_by_user_id")
    private User createdBy;

    // relation one to one with user
    @ManyToOne
    @JoinColumn(name = "updated_by_user_id")
    private User updatedBy;

    //relaion one to many with comment
    @OneToMany(mappedBy = "card")
    private java.util.List<Comment> comments;

    // relation with List
    @ManyToOne
    @JoinColumn(name = "list_id")
    private List list;

    //relation with project
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;


    //    For soft delete
    @Column(name = "is_deleted")
    private Boolean isDeleted = false;



    public void setAssignedTo(String s) {
        this.assignedTo = new User();
        this.assignedTo.setId(Integer.parseInt(s));
    }
}