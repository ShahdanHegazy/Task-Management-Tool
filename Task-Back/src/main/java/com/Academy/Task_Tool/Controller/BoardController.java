package com.Academy.Task_Tool.Controller;

import com.Academy.Task_Tool.DTO.*;
import com.Academy.Task_Tool.Services.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//import com.Academy.Task_Tool.Entity.List;
import com.Academy.Task_Tool.Entity.List;

@RestController
@RequestMapping("api")
public class BoardController {
    @Autowired
    BoardService boardService;


    @GetMapping("/{userId}")
    public ResponseEntity<java.util.List<ProjectDto>> getAllAssignedProjects(@PathVariable int userId) {
        return new ResponseEntity<>(boardService.getAllAssignedProjects(userId), HttpStatus.OK);
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<java.util.List<BoardDto>> getBoard(@PathVariable int projectId) {
        return new ResponseEntity<>(boardService.getBoard(projectId),HttpStatus.OK);
    }

    @PutMapping("/{projectId}/lists/{sourceListId}/cards/{cardId}/move/{targetListId}")
    public ResponseEntity<String> moveCard(@PathVariable int projectId,
                                         @PathVariable int sourceListId,
                                         @PathVariable int cardId,
                                         @PathVariable int targetListId) {

        return new ResponseEntity<>(boardService.moveCard(cardId, sourceListId, targetListId),HttpStatus.OK);
    }

    @PostMapping   ("/project/{projectId}")
    public ResponseEntity<String> addList(@RequestBody ListDto listDto, @PathVariable int projectId) {
        return new ResponseEntity<>(boardService.addList(listDto ,projectId),HttpStatus.CREATED);
    }


   @PutMapping ("/list/{listId}")
    public ResponseEntity<String> updateList(@RequestBody ListDto listUpdate, @PathVariable int listId) {
        return new ResponseEntity<>(boardService.updateList(listUpdate,listId),HttpStatus.OK);
   }

    @DeleteMapping("/{listId}")
    public ResponseEntity<String> softDeleteList(@PathVariable int listId) {
        return new ResponseEntity<>( boardService.softDeleteList(listId),HttpStatus.OK);
    }
}
