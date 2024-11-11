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
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="users")
public class User {
    @Id
    @Column(name="Id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name="Name",nullable = false)
    private String name;

    @Column(name="email",nullable = false,unique = true)
    private String email;

    @Column(name="password", nullable = false)
    private String password;


    @Column(name="create_at",nullable = false)
    private LocalDateTime createAt;

    @Where(clause = "deleted = false")
    @Column(name = "deleted")
    private Boolean deleted = false;
    public void markAsDeleted() {
        this.deleted = true;
    }

//    @JsonIgnore
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "role_id")
//    private Role role_id;

    @Column(name = "is_deleted")
    private Boolean isDeleted = false;

    @JsonBackReference
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;

}
