package com.Academy.Task_Tool.Services;

import com.Academy.Task_Tool.Entity.Comment;
import com.Academy.Task_Tool.Entity.Task;
import com.Academy.Task_Tool.Repository.CommentRepository;
import com.Academy.Task_Tool.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    CommentRepository commentRepository;

    
    public ResponseEntity<List<Task>> getAllTasks(Long projectId) {
        Optional<Task> optionalTask = taskRepository.findById(projectId);

        if (optionalTask.isPresent()) {
            List<Task> tasks = new ArrayList<>();
            tasks.add(optionalTask.get());
            return new ResponseEntity<>(tasks, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<String> updateTaskStatus(Task task, int projectId, int taskId) {
        taskRepository.findById(taskId);
        taskRepository.save(task);
        return new ResponseEntity<>( "Task updated successfully", HttpStatus.OK);
    }

    public ResponseEntity<Task> getTask(int projectId, int taskId) {
         taskRepository.findById(taskId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<Comment> createComment(Comment comment ,int projectId, int taskId) {
        commentRepository.save(comment);
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    public ResponseEntity<Comment> updateComment(Comment comment , int projectId, int taskId, int commentId) {
        Comment updated = commentRepository.findById(commentId).orElseThrow();
        updated.setContent(comment.getContent());
        updated.setUpdate_at(comment.getUpdate_at());
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

        public ResponseEntity<String> deleteComment(int projectId, int taskId , int commentId) {
        commentRepository.deleteById(commentId);
        return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
    }
}
