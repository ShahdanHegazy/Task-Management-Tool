package com.Academy.Task_Tool.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;


@Entity
@Table(name = "comment")
@Getter
@Setter
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

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    @ManyToOne
    @JoinColumn(name = "user_id" , nullable = false)
    private User user;


    public Task getTask_id() {
        return task;
    }

    public void setUser(List<User> users) {
        this.user = user;
    }


}
