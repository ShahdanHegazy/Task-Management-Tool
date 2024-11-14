package com.Academy.Task_Tool.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;


@Entity
@Table(name = "comment")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data

public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id" )
    private Integer commentid;

    @Column(name = "creat_at" )
    private Timestamp creat_at;

    @Column(name = "content")
    private String content;

    @Column(name = "update_at")
    private String update_at ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id", nullable = false)
    private Card card;

    @ManyToOne
    @JoinColumn(name = "user_id" , nullable = false)
    private User user;


}
