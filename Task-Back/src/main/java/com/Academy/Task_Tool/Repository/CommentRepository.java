package com.Academy.Task_Tool.Repository;

import com.Academy.Task_Tool.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
    public interface CommentRepository extends JpaRepository<Comment, Integer> {
        List<Comment> findByCardCardIdAndIsDeletedFalse(Integer cardId); // Get all non-deleted comments for a task

        Optional<Comment> findByCommentidAndCardCardIdAndIsDeletedFalse(Integer commentId, Integer cardId); // Find specific comment for a task
    }

