package com.Academy.Task_Tool.Repository;

import com.Academy.Task_Tool.DTO.UserDto;
import com.Academy.Task_Tool.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    Boolean existsByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.isDeleted = false")
    List<User> findAllActiveUsers();

    @Query("SELECT COUNT(u) FROM User u WHERE u.role.id = :roleId")
    long countUsersByRoleId(Integer roleId);
}
