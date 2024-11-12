package com.Academy.Task_Tool.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name="roles")
public class Role {
    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer role_id;

    @Column(name="role_name")
    private String roleName;


// Optional: If you want to see users assigned to each role
     @JsonManagedReference
     @OneToMany(mappedBy = "role")
     private List<User> users;

}
