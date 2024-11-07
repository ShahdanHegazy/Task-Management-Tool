package com.Academy.Task_Tool.Controller;

import com.Academy.Task_Tool.DTO.UserDto;
import com.Academy.Task_Tool.Entity.User;
import com.Academy.Task_Tool.Services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AdminController {
    @Autowired
    private AdminService adminService;

    // Soft delete a user
    @PutMapping("/softDelete/{userId}")
    public ResponseEntity<String> softDeleteUser(@PathVariable Integer userId) {
        adminService.softDeleteUser(userId);
        return ResponseEntity.ok("User marked as deleted successfully");
    }

            // PUT: Update an existing user
        @PutMapping("/updateUser/{userId}")
        public ResponseEntity<UserDto> updateUser(@PathVariable("userId") Integer userId, @RequestBody UserDto userDetails) {
            UserDto user = adminService.updateUser(userId, userDetails);
            return ResponseEntity.ok(user);
    }


}

