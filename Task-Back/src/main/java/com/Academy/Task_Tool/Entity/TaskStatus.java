package com.Academy.Task_Tool.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Table(name = "task_status")
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class TaskStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "status_id")
    private Integer status_id;

    @Column(name = "status_name")
    private String status_name;

}
