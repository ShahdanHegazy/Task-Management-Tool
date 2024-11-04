package com.Academy.Task_Tool.Repository;

import com.Academy.Task_Tool.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepsitory extends JpaRepository<User,Integer> {

}
