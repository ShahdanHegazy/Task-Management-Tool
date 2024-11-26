package com.Academy.Task_Tool.Repository;

import com.Academy.Task_Tool.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import java.util.List;
import org.springframework.data.domain.Pageable;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    Boolean existsByEmail(String email);

//    @Query("SELECT u FROM User u WHERE u.isDeleted = false ")
//    List<User> findAllActiveUsers();


    @Query("SELECT u FROM User u WHERE u.isDeleted = false  ORDER BY u.role.role_id ASC ")
    Page<User> findAllActiveUsers(Pageable pageable);

  
    @Query(value = "SELECT u.id AS id, u.name AS name " + "FROM users u " +
            "WHERE u.role_id = 2 AND u.is_deleted = false", nativeQuery = true)
    List<Object[]> findAllActiveProjectManagers();

    
    @Query("SELECT COUNT(u) FROM User u WHERE u.role.role_id = :roleId AND u.isDeleted = false")
    long countUsersByRoleId(Integer roleId);

   User findUserByEmail(String email);

    // in User Repsitory file
    // Custom query to count users by role
//    @Query("SELECT u.role AS role, COUNT(u) AS count FROM User u GROUP BY u.role")
//    List<Map<String, Object>> countUsersByRole_id(Integer role_id);

//    @Query("SELECT COUNT(u) FROM User u WHERE u.role.role_id = :roleId AND u.isDeleted = false")
//    long countUsersByRoleId(Integer roleId);
}
