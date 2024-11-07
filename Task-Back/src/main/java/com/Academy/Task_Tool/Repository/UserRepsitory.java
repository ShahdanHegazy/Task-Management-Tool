package com.Academy.Task_Tool.Repository;

import com.Academy.Task_Tool.Entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepsitory extends JpaRepository<User,Integer> {

    // Find by id, excluding soft deleted records
    Optional<User> findByIdAndDeletedFalse(Integer userId);

    // Custom query to perform a soft delete on a user
    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.deleted = true WHERE u.id = :id")
    void softDeleteUserById(Integer id);
}
