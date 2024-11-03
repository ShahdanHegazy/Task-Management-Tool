package com.Academy.Task_Tool.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;


@Entity
@Table(name = "comment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id" )
    private Integer commentid;

    @Column(name = "task_id" )
    private Integer task_id;

    @Column(name = "creat_at" )
    private Timestamp creat_at;

    @Column(name = "content")
    private String content;

    @Column(name = "user_id")
    private Integer user_id;

    @Column(name = "update_at")
    private String update_at ;
}
