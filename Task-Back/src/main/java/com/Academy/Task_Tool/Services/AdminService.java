package com.Academy.Task_Tool.Services;

import com.Academy.Task_Tool.DTO.UserDto;
import com.Academy.Task_Tool.DTO.UserResponseDto;
import com.Academy.Task_Tool.DTO.UserUpDataDto;
import com.Academy.Task_Tool.Entity.Role;
import com.Academy.Task_Tool.Entity.User;
import com.Academy.Task_Tool.Repository.ProjectRepository;
import com.Academy.Task_Tool.Repository.RoleRepository;
import com.Academy.Task_Tool.Repository.UserRepository;
import com.Academy.Task_Tool.Repository.UserRepsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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







    private UserRepository userRepsitory;

    @Autowired
    private RoleRepository roleRepository;

    public User createUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setCreateAt(LocalDateTime.now());
        user.setIsDeleted(false);

        // Fetch and set role
        Role role = roleRepository.findById(userDto.getRoleId())
                .orElseThrow(() -> new RuntimeException("Role not found"));
        user.setRole(role);

        return userRepsitory.save(user);
    }

    public List<UserResponseDto> getAllUsersWithRole() {
        return userRepsitory.findAll().stream()
                .map(user -> new UserResponseDto(
                        user.getName(),
                        user.getEmail(),
                        user.getRole().getRole_id()
                ))
                .collect(Collectors.toList());
    }

    public void softDeleteUser(Integer id) {
        User user = userRepsitory.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setIsDeleted(true);  // Mark the user as deleted
        userRepsitory.save(user); // Save the updated user
    }

    public List<User> getAllActiveUsers() {
        return userRepsitory.findAllActiveUsers(); // Fetch non-deleted users
    }

    public User updateUser(Integer userId, UserUpDataDto userUpdateDto) {
        User user = userRepsitory.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Update fields if provided
        if (userUpdateDto.getName() != null) {
            user.setName(userUpdateDto.getName());
        }
        if (userUpdateDto.getEmail() != null) {
            user.setEmail(userUpdateDto.getEmail());
        }
//        if (userUpdateDto.getPassword() != null) {
//            user.setPassword(userUpdateDto.getPassword());
//        }
        if (userUpdateDto.getRoleId() != null) {
            Role role = roleRepository.findById(userUpdateDto.getRoleId())
                    .orElseThrow(() -> new RuntimeException("Role not found"));
            user.setRole(role);
        }
        return userRepsitory.save(user);  // Save and return the updated user
    }


}
