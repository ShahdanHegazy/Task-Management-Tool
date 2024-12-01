package com.Academy.Task_Tool.Controller;

import com.Academy.Task_Tool.DTO.*;
import com.Academy.Task_Tool.Services.BoardService;

import java.util.HashMap;
import java.util.Map;

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

    // @PreAuthorize("hasAnyRole('ROLE_PM ','ROLE_MEMBER')")
    // @PutMapping("/{projectId}/lists/{sourceListName}/cards/{cardId}/move/{targetListName}")
    // public ResponseEntity<String> moveCard(@PathVariable int projectId,
    //                                      @PathVariable String sourceListName,
    //                                      @PathVariable int cardId,
    //                                      @PathVariable String targetListName) {

    //     return new ResponseEntity<>(boardService.moveCard( projectId,cardId, sourceListName, targetListName),HttpStatus.OK);
    // }
    @PreAuthorize("hasAnyRole('ROLE_PM', 'ROLE_MEMBER')")
    @PutMapping("/{projectId}/lists/{sourceListName}/cards/{cardId}/move/{targetListName}")
    public ResponseEntity<Map<String, String>> moveCard(@PathVariable int projectId,
                                                        @PathVariable String sourceListName,
                                                        @PathVariable int cardId,
                                                        @PathVariable String targetListName) {
        // استدعاء الخدمة والحصول على الرسالة
        String resultMessage = boardService.moveCard(projectId, cardId, sourceListName, targetListName);
    
        // إنشاء استجابة JSON
        Map<String, String> response = new HashMap<>();
        response.put("message", resultMessage);
    
        // إرجاع كائن ResponseEntity مع JSON
        return ResponseEntity.ok(response);
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
