package com.Academy.Task_Tool.Controller;

import com.Academy.Task_Tool.Services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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


}

