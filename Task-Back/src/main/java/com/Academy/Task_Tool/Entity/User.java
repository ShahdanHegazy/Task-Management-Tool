package com.Academy.Task_Tool.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.Data;
import org.hibernate.annotations.Where;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="users")
public class User {
    @Id
    @Column(name="Id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name")
    private String name;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;


    @Column(name="create_at")
    private LocalDateTime createAt;

    //Relations

    //relation with comment
    @OneToMany(mappedBy = "user")
    private Set<Comment> comments;

    //relation with role
    @JsonBackReference
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;

    //relation with projects
    @JsonBackReference
    @ManyToMany
    @JoinTable(
            name = "user_project",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    private Set<Project> assignedProjects;

    // For soft delete
    @Column(name = "is_deleted")
    private Boolean isDeleted = false;

    //    @Where(clause = "deleted = false")
//    @Column(name = "deleted")
//    private Boolean deleted = false;
//    public void markAsDeleted() {
//        this.deleted = true;
//    }

}
