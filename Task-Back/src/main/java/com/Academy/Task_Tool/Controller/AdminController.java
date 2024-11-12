package com.Academy.Task_Tool.Controller;

import com.Academy.Task_Tool.DTO.*;
import com.Academy.Task_Tool.Entity.Project;
import com.Academy.Task_Tool.Entity.User;
import com.Academy.Task_Tool.Repository.UserRepository;
import com.Academy.Task_Tool.Repository.UserRepository;
import com.Academy.Task_Tool.Services.AdminService;
import jakarta.validation.Valid;
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
    private AdminService adminService;
    @Autowired
    private UserRepository userRepository;

    // GET endpoint to fetch project count
    @GetMapping("/count/project")
    public ResponseEntity<Integer> getprojectCount() {
        Integer projectCount = adminService.getProjectCount();
        return ResponseEntity.ok(projectCount);
    }

    // GET endpoint to fetch user count by role_id
//    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // Endpoint to get user count for a specific role_id
    @GetMapping("/count-by-role-id/{roleId}")
    public long getUserCountByRoleId(@PathVariable Integer roleId) {
        return adminService.getUserCountByRoleId(roleId);
    }


    @PostMapping("/admin/createUser")
    public UserDto createUser(@Valid @RequestBody UserDto userDto) {
        return adminService.createUser(userDto);
    }
//  endpoint for retrieve all users
    @GetMapping("/admin/all-users-with-roles")
    public List<UserResponseDto> getAllUsersWithRoles() {
        return adminService.getAllUsersWithRole();
    }

// endpoint for delete user by id
    @DeleteMapping("/admin/delete/{id}")
    public String softDeleteUser(@PathVariable Integer id) {
        adminService.softDeleteUser(id);
        return "User soft deleted successfully";
    }

//   @DeleteMapping("/admin/delete/{id}")
//   public UserResponseDto softDeleteUser(@PathVariable Integer id) {
//    User user=adminService.softDeleteUser(id);
//
//    UserResponseDto userResponseDto=new UserResponseDto();
//     userResponseDto.setName(user.getName());
//     userResponseDto.setEmail(user.getEmail());
//     userResponseDto.setRoleId(user.getRole().getRole_id());
//    return userResponseDto;
//}

// endpoint for retrieve active user
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
    public Project createProject(@RequestBody ProjectDto projectDto) {
        return adminService.createProject(projectDto);
    }

    @GetMapping("/admin/project-managers")
    public List<ProjectManagerDto> getAllProjectManagers() {
        return adminService.getAllProjectManager();
    }

//    @GetMapping("/admin/projectDetails/{id}")
//    public ProjectDto getAllDetailsProject(@PathVariable Integer id) {
//        return adminService.getAllDetailsProject(id);
//    }

//    @PutMapping("/admin/updateproject/{id}")
//    public Project updateProject(@PathVariable Integer id , )
//
//    @GetMapping("/admin/list-project")
//    public List<ProjectResponseDto> getAllProjects() {
//        return adminService.getAllProject();
//    }


}
