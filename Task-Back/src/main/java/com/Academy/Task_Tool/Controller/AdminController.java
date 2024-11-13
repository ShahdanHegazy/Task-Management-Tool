package com.Academy.Task_Tool.Controller;

import com.Academy.Task_Tool.DTO.*;
import com.Academy.Task_Tool.Repository.UserRepository;
import com.Academy.Task_Tool.Services.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AdminController {
    @Autowired
    private final AdminService adminService;
    @Autowired
    private UserRepository userRepository;

    // GET endpoint to fetch project count
    @GetMapping("/count/project")
    public ResponseEntity<Integer> getprojectCount() {
        Integer projectCount = adminService.getProjectCount();
        return ResponseEntity.ok(projectCount);
    }

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // Endpoint to get user count for a specific role_id
    @GetMapping("/count-by-role-id/{roleId}")
    public long getUserCountByRoleId(@PathVariable Integer roleId) {
        return adminService.getUserCountByRoleId(roleId);
    }
 ////////////////////////////////////////////////////////////////////////////////

    @PostMapping(value = "/admin/createUser",consumes ="application/json",produces = "application/json")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        UserDto createdUser = adminService.createUser(userDto);
        return ResponseEntity.status(HttpStatus.OK).body(createdUser);
    }
//  endpoint for retrieve all users active and non active ;
//    @GetMapping("/admin/all-users-with-roles")
//    public List<UserResponseDto> getAllUsersWithRoles() {
//        return adminService.getAllUsersWithRole();
//    }

// endpoint for delete user by id
    @DeleteMapping("/admin/delete/{id}")
    public String softDeleteUser(@PathVariable Integer id) {
        adminService.softDeleteUser(id);
        return "User soft deleted successfully";
    }

// endpoint for retrieve All active user
    @GetMapping("/admin/active/users")
    public List<UserResponseDto> getAllActiveUsers() {
        return adminService.getAllActiveUsers();
    }

    @PutMapping("/admin/update/{userId}")
    public  UserUpDataDto updateUser(@PathVariable Integer userId, @RequestBody UserUpDataDto userUpdateDto) {
        return adminService.updateUser(userId, userUpdateDto);
    }

    //////////////////////////////////////////////////////////////////

    @PostMapping("/admin/createProject")
    public ProjectDto createProject(@RequestBody ProjectDto projectDto) {
        return adminService.createProject(projectDto);
    }

    @GetMapping("/admin/project-managers")
    public List<ProjectManagerDto> getAllProjectManagers() {
        return adminService.getAllActiveProjectManagers();
    }

    @DeleteMapping("/admin/deleteProject/{id}")
    public void softDeleteProject(@PathVariable Integer id) {
        adminService.softDeleteProject(id);
    }

    @PutMapping("/admin/updateProject/{projectId}")
    public  ProjectUpDateDto updateProject(@PathVariable Integer projectId, @RequestBody ProjectUpDateDto userUpdateDto) {
        return adminService.updateProject(projectId, userUpdateDto);
    }

    @GetMapping("/admin/active/projects")
    public List<ProjectResponseDto> getAllActiveProjects() {
        return adminService.getAllActiveProjects();
    }

}
