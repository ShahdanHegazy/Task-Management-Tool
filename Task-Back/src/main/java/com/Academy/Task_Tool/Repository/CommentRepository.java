package com.Academy.Task_Tool.Repository;

import com.Academy.Task_Tool.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Integer> {
}
