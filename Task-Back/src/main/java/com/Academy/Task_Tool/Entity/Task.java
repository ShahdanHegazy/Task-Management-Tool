package com.Academy.Task_Tool.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
@Table(name="tasks")
public class Task {
    @Id
    @Column(name = "task_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer taskId;

    @Column(name="project_id")
    private Integer projectId;

    @Column(name="title")
    private String title;

    @Column(name="status_id")
    private Integer statusId;

    @Column(name="due_date")
    private Timestamp dueDate;

    @Column(name="user_id")
    private Integer userId;

    @Column(name="description")
    private String description;

    @Column(name="priority")
    private String  priority;

    @Column(name="create_at")
    private Timestamp createAt;

    @Column(name="create_by")
    private String createBy;

    @OneToMany(mappedBy = "task")
    private List<Comment> comments;


}
