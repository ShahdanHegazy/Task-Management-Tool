package com.Academy.Task_Tool.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
@Table(name="tasks")
public class Card {
    @Id
    @Column(name = "task_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer taskId;


    @Column(name="title")
    private String title;

    @Column(name="due_date")
    private Timestamp dueDate;

    @Column(name="description")
    private String description;

    @Column(name="priority")
    private String  priority;

    @Column(name="create_at")
    private Timestamp createAt;

    @Column(name="assigned_to")
    private String assignedTo;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "project_id")
    private Project project;

    @OneToMany(mappedBy = "task")
    private List<Comment> comments;


}
