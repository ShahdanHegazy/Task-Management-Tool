package com.Academy.Task_Tool.Services;

import com.Academy.Task_Tool.DTO.UserDto;
import com.Academy.Task_Tool.Entity.Role;
import com.Academy.Task_Tool.Entity.User;
import com.Academy.Task_Tool.Repository.RoleRepository;
import com.Academy.Task_Tool.Repository.UserRepsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private UserRepsitory userRepsitory;
    @Autowired
    private RoleRepository roleRepository;

    public void softDeleteUser(Integer userId) {
        User user = userRepsitory.findByIdAndDeletedFalse(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id " + userId));

        user.markAsDeleted();  // Set the deleted flag to true
        userRepsitory.save(user);  // Save the updated entity


    }

        public UserDto updateUser(Integer userid , UserDto userDto) {
        User user = userRepsitory.findById(userid).orElseThrow(() -> new RuntimeException("User not found with id " + userid));
            user.setName(userDto.getUsername());
            user.setEmail(userDto.getEmail());
            user.setPassword(userDto.getPassword());
            if (userDto.getRoleId() != null) {
                Role role = roleRepository.findById(userDto.getRoleId())
                        .orElseThrow(() -> new RuntimeException("Role not found with id " + userDto.getRoleId()));
                role.setRoleId(userDto.getRoleId());
                user.setRole(role);
                roleRepository.save(role);
            }
            User usersaved = userRepsitory.save(user);
            UserDto userDtosaved = new UserDto();
            userDtosaved.setUsername(user.getName()); // Assuming 'name' corresponds to 'username'
            userDtosaved.setEmail(user.getEmail());
            userDtosaved.setPassword(user.getPassword());
            userDtosaved.setRoleId(usersaved.getRole().getRoleId());

        return  userDtosaved ;
    }




}
