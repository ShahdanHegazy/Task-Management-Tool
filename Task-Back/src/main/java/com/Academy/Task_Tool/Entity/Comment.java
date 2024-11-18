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

    @Column(name = "content")
    private String content;

    @Column(name = "create_at" )
    private Timestamp create_at;


    @Column(name = "update_at")
    private String update_at ;

    //Relations

    // relation with card
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id", nullable = false)
    private Card card;

    // relation with user
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    //For soft delete
    @Column(name = "is_deleted")
    private Boolean isDeleted = false;
}
