package com.Academy.Task_Tool.Controller;

import com.Academy.Task_Tool.DTO.UserDto;
import com.Academy.Task_Tool.DTO.UserResponseDto;
import com.Academy.Task_Tool.DTO.UserUpDataDto;
import com.Academy.Task_Tool.Entity.User;
import com.Academy.Task_Tool.Services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AdminController {
    @Autowired
    private AdminService adminservice;
    // GET endpoint to fetch project count
    @GetMapping("/count/project")
    public ResponseEntity<Integer> getprojectCount() {
        Integer projectCount = adminservice.getProjectCount();
        return ResponseEntity.ok(projectCount);
    }





    // GET endpoint to fetch user count by role_id
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // Endpoint to get user count for a specific role_id
    @GetMapping("/count-by-role-id/{roleId}")
    public long getUserCountByRoleId(@PathVariable Integer roleId) {
        return adminService.getUserCountByRoleId(roleId);
    }


    @PostMapping("/create")
    public User createUser(@RequestBody UserDto userDto) {
        return adminService.createUser(userDto);
    }
//  endpoint for retrieve all users
    @GetMapping("/all-users-with-roles")
    public List<UserResponseDto> getAllUsersWithRoles() {
        return adminService.getAllUsersWithRole();
    }

// endpoint for delete user by id
    @DeleteMapping("/delete/{id}")
    public String softDeleteUser(@PathVariable Integer id) {
        adminService.softDeleteUser(id);
        return "User soft deleted successfully";
    }

// endpoint for retrieve active user
    @GetMapping("/active/users")
    public List<User> getAllActiveUsers() {
        return adminService.getAllActiveUsers();
    }

    @PutMapping("/update/{userId}")
    public User updateUser(@PathVariable Integer userId, @RequestBody UserUpDataDto userUpdateDto) {
        return adminService.updateUser(userId, userUpdateDto);
    }




}
