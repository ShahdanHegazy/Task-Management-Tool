package com.Academy.Task_Tool.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Where;

import java.sql.Timestamp;

@Entity
@Data
@Table(name="users")
public class User {
    @Id
    @Column(name="Id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name="Name")
    private String name;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="create_at")
    private Timestamp createAt;

    @Where(clause = "deleted = false")
    @Column(name = "deleted")
    private Boolean deleted = false;
    public void markAsDeleted() {
        this.deleted = true;
    }

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;


}
