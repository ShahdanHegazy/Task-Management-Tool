package com.Academy.Task_Tool.Services;
import com.Academy.Task_Tool.Entity.Comment;
import com.Academy.Task_Tool.Entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectManagerService {

//    public Comment updateComment(int commentid , Comment commentDetails) {
//        Comment comment = commentRepository.findById(commentid).orElseThrow(() -> new RuntimeException("Comment not found with id " + commentid));
//        comment.setUpdate_at(commentDetails.getUpdate_at());
//        comment.setContent(commentDetails.getContent());
//        comment.setUser((List<User>) commentDetails.getUser());
//        comment.setTask(commentDetails.getTask_id());
//        comment.setCreat_at(commentDetails.getCreat_at());
//
//        return commentRepository.save(comment);
//    }
//
//    public void deleteComment(int id) {
//        Comment comment = commentRepository.findById(id).orElseThrow(() -> new RuntimeException("Comment not found with id " + id));
//        commentRepository.delete(comment);
//    }



}
