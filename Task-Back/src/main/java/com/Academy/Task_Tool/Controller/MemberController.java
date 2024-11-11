package com.Academy.Task_Tool.Controller;

import com.Academy.Task_Tool.DTO.ProjectDto;
import com.Academy.Task_Tool.DTO.TaskDto;
import com.Academy.Task_Tool.Entity.Comment;
import com.Academy.Task_Tool.Entity.Task;
import com.Academy.Task_Tool.Services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/member")
public class MemberController {
    @Autowired
    MemberService memberService;


    @GetMapping("/{userId}")
    public ResponseEntity<List<ProjectDto>> getAllAssignedProjects(@PathVariable int userId) {
        return memberService.getAllAssignedProjects(userId);
    }
    @GetMapping("/project{projectId}/alltasks")
    public ResponseEntity<List<TaskDto>> getProjectWithTasks(@PathVariable int projectId) {
        return memberService.getProjectWithTasks(projectId);
    }

    @PutMapping("/project{projectId}/taskStatus{taskId}")
    public ResponseEntity<String> moveTask(@PathVariable int taskId , @PathVariable int sourceListId , @PathVariable int targetListId) {
        return memberService.moveTask(taskId , sourceListId ,targetListId);
    }


//    @GetMapping ("/project{projectId}/task{taskId}")
//    public ResponseEntity<Task> getTask(@PathVariable int projectId, @PathVariable int taskId) {
//        return memberService.getTask(projectId,taskId);
//    }
//
//    @PostMapping("/project{projectId}/task{taskId}")
//    public ResponseEntity<Comment> createComment(@RequestBody Comment comment,@PathVariable int projectId, @PathVariable int taskId) {
//        return memberService.createComment(comment,projectId,taskId);
//    }
//
//    @PutMapping("/project{projectId}/task{taskId}/{commentId}")
//    public ResponseEntity<Comment> updateComment(@RequestBody Comment comment,@PathVariable int projectId, @PathVariable int taskId, @PathVariable int commentId) {
//        return memberService.updateComment(comment,projectId,taskId,commentId);
//    }
//
//    @DeleteMapping("/project{projectId}/task{taskId}")
//    public ResponseEntity<String> deleteComment(@PathVariable int projectId, @PathVariable int taskId,@PathVariable int commentId) {
//        return memberService.deleteComment(projectId,taskId,commentId);
//    }

}
