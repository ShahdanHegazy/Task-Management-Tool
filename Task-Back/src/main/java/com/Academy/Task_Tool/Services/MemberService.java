package com.Academy.Task_Tool.Services;

import com.Academy.Task_Tool.DTO.ProjectDto;
import com.Academy.Task_Tool.DTO.TaskDto;
import com.Academy.Task_Tool.Entity.*;
import com.Academy.Task_Tool.Repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MemberService {
    
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    TaskStatusRepository taskStatusRepository;

//get all assigned projects
    public ResponseEntity<List<ProjectDto>> getAllAssignedProjects(int userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("User not found")
        );
        Set<Project> projects = user.getAssignedProjects();
        List<ProjectDto> projectDTOs = projects.stream()
                .map(project -> {
                    ProjectDto projectDTO = new ProjectDto();
                    projectDTO.setProject_id(project.getProject_id());
                    projectDTO.setTitle(project.getTitle());
                    return projectDTO;
                })
                .collect(Collectors.toList());

        return new ResponseEntity<>(projectDTOs, HttpStatus.OK);
    }


    //get all tasks
    public ResponseEntity<List<TaskDto>> getProjectWithTasks(int projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow(
                ()->new RuntimeException("Project not found")
        );
        Set<Task> tasks = project.getAssignedTasks();
        List<TaskDto> taskDtos = tasks.stream()
                .map(
                        task -> {
                            TaskDto taskDTO = new TaskDto();
                            taskDTO.setTaskId(task.getTaskId());
                            taskDTO.setTitle(task.getTitle());
                            taskDTO.setDescription(task.getDescription());
                            taskDTO.setPriority(task.getPriority());
                            taskDTO.setPriority(task.getPriority());
                            taskDTO.setDueDate(task.getDueDate());
                            taskDTO.setCreateAt(task.getCreateAt());
                            taskDTO.setAssignedTo(task.getAssignedTo());
                            return taskDTO;
                        }).collect(Collectors.toList());

        return new ResponseEntity<>(taskDtos, HttpStatus.OK);
    }

    public ResponseEntity<String> moveTask(int taskId, int sourceListId, int targetListId) {
        Task task = taskRepository.findById(taskId).orElseThrow(()-> new EntityNotFoundException("Task not found"));
//        List sourceList = taskStatusRepository.findById(sourceListId).orElseThrow(()-> new EntityNotFoundException("Task status not found"));
//        List targetList = taskStatusRepository.findById(targetListId).orElseThrow(()-> new EntityNotFoundException("Task status not found"));

        return null;
    }

    // update task status



//    //get task
//    public ResponseEntity<Task> getTask(int projectId, int taskId) {
//         taskRepository.findById(taskId);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    //create comment
//    public ResponseEntity<Comment> createComment(Comment comment ,int projectId, int taskId) {
//        commentRepository.save(comment);
//        return new ResponseEntity<>(comment, HttpStatus.CREATED);
//    }
//
//    // update comment
//    public ResponseEntity<Comment> updateComment(Comment comment , int projectId, int taskId, int commentId) {
//        Comment updated = commentRepository.findById(commentId).orElseThrow();
//        updated.setContent(comment.getContent());
//        updated.setUpdate_at(comment.getUpdate_at());
//        return new ResponseEntity<>(updated, HttpStatus.OK);
//    }
//
//    // delete comment
//
//        public ResponseEntity<String> deleteComment(int projectId, int taskId , int commentId) {
//        commentRepository.deleteById(commentId);
//        return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
//    }


}
