package com.Academy.Task_Tool.Controller;

import com.Academy.Task_Tool.DTO.*;
import com.Academy.Task_Tool.Services.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

//import com.Academy.Task_Tool.Entity.List;
import com.Academy.Task_Tool.Entity.List;

@RestController
@RequestMapping("/api")
public class BoardController {
    @Autowired
    BoardService boardService;


    @PreAuthorize("hasRole('ROLE_MEMBER')")

    @GetMapping("member/{userId}")
    public ResponseEntity<java.util.List<AssignedProjDto>> getAllAssignedProjects(@PathVariable int userId) {
        return new ResponseEntity<>(boardService.getAllAssignedProjects(userId), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_PM')")

    @GetMapping("pm/{userId}")
    public ResponseEntity<java.util.List<AssignedProjDto>> getAllManagedProjects(@PathVariable int userId) {
        return new ResponseEntity<>(boardService.getAllManagedProjects(userId), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_PM ','ROLE_MEMBER')")
    @GetMapping("/project/{projectId}")
    public ResponseEntity<ProjectBoardDto> getBoard(@PathVariable int projectId) {
        return new ResponseEntity<>(boardService.getBoard(projectId),HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_PM ','ROLE_MEMBER')")
    @PutMapping("/{projectId}/lists/{sourceListId}/cards/{cardId}/move/{targetListId}")
    public ResponseEntity<String> moveCard(@PathVariable int projectId,
                                         @PathVariable int sourceListId,
                                         @PathVariable int cardId,
                                         @PathVariable int targetListId) {

        return new ResponseEntity<>(boardService.moveCard(cardId, sourceListId, targetListId),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_PM')")
    @PostMapping   ("/project/{projectId}")
    public ResponseEntity<ListDto> addList(@RequestBody ListDto listDto, @PathVariable int projectId) {
        return new ResponseEntity<>(boardService.addList(listDto ,projectId),HttpStatus.CREATED);
    }


    @PreAuthorize("hasRole('ROLE_PM')")
   @PutMapping ("/list/{listId}")
    public ResponseEntity<ListDto> updateList(@RequestBody ListDto listUpdate, @PathVariable int listId) {
        return new ResponseEntity<>(boardService.updateList(listUpdate,listId),HttpStatus.OK);
   }

    @PreAuthorize("hasRole('ROLE_PM')")
    @DeleteMapping("/{listId}")
    public ResponseEntity<String> softDeleteList(@PathVariable int listId) {
        return new ResponseEntity<>( boardService.softDeleteList(listId),HttpStatus.OK);
    }
}
