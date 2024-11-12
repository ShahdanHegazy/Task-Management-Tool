package com.Academy.Task_Tool.Repository;

import com.Academy.Task_Tool.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public interface UserRepsitory extends JpaRepository<User,Integer> {
    // Custom query to count users by role
//    @Query("SELECT u.role AS role, COUNT(u) AS count FROM User u GROUP BY u.role")
//    List<Map<String, Object>> countUsersByRole_id(Integer role_id);

    @Query("SELECT COUNT(u) FROM User u WHERE u.role.role_id = :roleId AND u.isDeleted = false")
    long countUsersByRoleId(Integer roleId);


}
