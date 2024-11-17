package com.Academy.Task_Tool.Repository;

import com.Academy.Task_Tool.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
//    List<Comment> findByCardIdAndDeletedFalse(Integer cardId);
//    Optional<Comment> findByIdAndDeletedFalse(Integer    commentId);
}
