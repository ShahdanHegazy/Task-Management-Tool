package com.Academy.Task_Tool.Controller;//package com.Academy.Task_Tool.Controller;
import com.Academy.Task_Tool.DTO.CardDto;
import com.Academy.Task_Tool.DTO.CommentDto;
import com.Academy.Task_Tool.DTO.ProjectMemberAssignmentDto;
import com.Academy.Task_Tool.Entity.Card;
import com.Academy.Task_Tool.Entity.Project;
import com.Academy.Task_Tool.Services.ProjectManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pm")
public class ProjectManagerController {

    @Autowired
    private ProjectManagerService projectManagerService;

//    @GetMapping("/project{projectId}/alltasks")
//    public ResponseEntity<List<CardDto>> getProjectWithTasks(@PathVariable int projectId) {
//        return projectManagerService.getProjectWithCards(projectId);
//    }


//  GET ALL CARD
    @GetMapping("/Card/{cardId}")
    public CardDto getCardById(@PathVariable Integer cardId) {
        return projectManagerService.getCardById(cardId);
    }

//    CREAT CARD BY PROJECT ID
//@PostMapping("/projects/{projectId}/cards")
//public CardDto createCard(@RequestBody CardDto cardDto , @PathVariable Integer projectId) {
//    return projectManagerService.createCard(cardDto);
//}

//  UPDATE CARD
    @PutMapping("/cards/{cardId}")
    public CardDto updateCard(@PathVariable Integer cardId, @RequestBody CardDto cardDto) {
        return projectManagerService.updateCard(cardId, cardDto);
    }

//    SOFT DELETE FOR CARD
@DeleteMapping("/cards/{cardId}")
public void deleteCard(@PathVariable Integer cardId) {
    projectManagerService.deleteCard(cardId);
}

////  ASSIGIN MEMBER TO PROJECT
@Autowired
private ProjectManagerService projectManagerService1;

    @PostMapping("/assign-user")
    public ResponseEntity<Project> assignUserToProject(@RequestBody ProjectMemberAssignmentDto dto) {
        Project updatedProject = projectManagerService1.assignUserToProject(dto);
        return ResponseEntity.ok(updatedProject);
    }


//    CRUD For Comment

    // Retrieve all comments for a specific card
    @GetMapping("/{CardId}/comments")
    public List<CommentDto> getCommentsByCardId(@PathVariable Integer CardId) {
         return projectManagerService.getCommentsByCardId(CardId);
    }

    // Retrieve a specific comment by commentId and taskId
    @GetMapping("/{cardId}/comments/{commentId}")
    public CommentDto getCommentByIdAndTaskId(@PathVariable Integer cardId, @PathVariable Integer commentId) {
        return projectManagerService.getCommentByIdAndCardId(commentId , cardId);
    }

    // Create a new comment for a task
    @PostMapping("/{cardId}/comments")
    public CommentDto createComment(@PathVariable Integer cardId, @RequestBody CommentDto commentDTO) {
        return projectManagerService.createComment(cardId, commentDTO);
    }

    // Update an existing comment for a task
    @PutMapping("/{cardId}/comments/{commentId}")
    public CommentDto updateComment(@PathVariable Integer cardId, @PathVariable Integer commentId, @RequestBody CommentDto commentDTO) {
        return projectManagerService.updateComment(commentId, cardId, commentDTO);
    }

    // Soft delete a comment for a task
    @DeleteMapping("/{cardId}/comments/{commentId}")
    public void deleteComment(@PathVariable Integer cardId, @PathVariable Integer commentId) {
        projectManagerService.deleteComment(commentId, cardId);

    }



}

