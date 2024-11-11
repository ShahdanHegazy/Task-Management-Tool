package com.Academy.Task_Tool.Services;

import com.Academy.Task_Tool.Repository.ProjectRepository;
import com.Academy.Task_Tool.Repository.UserRepsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AdminService {
    // GET endpoint to fetch project count
    @Autowired
    private ProjectRepository projectRepository;
    private Object role_id;

    public Integer getProjectCount() {
        return projectRepository.countAllProject();
    }




    // GET endpoint to fetch user count by role_id
    private final UserRepsitory userRepository;

    @Autowired
    public AdminService(UserRepsitory userRepository) {
        this.userRepository = userRepository;
    }

    public long getUserCountByRoleId(Integer roleId) {
        return userRepository.countUsersByRoleId(roleId);
    }








}
