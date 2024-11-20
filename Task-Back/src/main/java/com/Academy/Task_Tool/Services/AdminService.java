package com.Academy.Task_Tool.Services;

import com.Academy.Task_Tool.DTO.*;
import com.Academy.Task_Tool.Entity.Project;
import com.Academy.Task_Tool.Entity.Role;
import com.Academy.Task_Tool.Entity.User;
import com.Academy.Task_Tool.Repository.ProjectRepository;
import com.Academy.Task_Tool.Repository.RoleRepository;
import com.Academy.Task_Tool.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService {
    // GET endpoint to fetch project count
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private  UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private RoleRepository roleRepository;


  
// total of projects
    public Integer getProjectCount() {
        return projectRepository.countAllProject();
    }

    //??????????????????????????????
    // GET endpoint to fetch user count by role_id
    @Autowired
    public AdminService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public long getUserCountByRoleId(Integer roleId) {
        return userRepository.countUsersByRoleId(roleId);
    }

/////////////////////////////////////////////////////////////////

    // method for user within admin (create user)
    public UserDto createUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        user.setCreateAt(LocalDateTime.now());
        user.setIsDeleted(false);

        // Fetch and set role
        Role role = roleRepository.findById(userDto.getRoleId())
                .orElseThrow(() -> new RuntimeException("Role not found"));
        user.setRole(role);

        User savedUser =userRepository.save(user);
        // convert savedUser to UserDto
        UserDto savedUserDto=new UserDto();
        savedUserDto.setId(user.getId());
        savedUserDto.setName(savedUser.getName());
        savedUserDto.setEmail(savedUser.getEmail());
        savedUserDto.setPassword(savedUser.getPassword());
        savedUserDto.setRoleId(role.getRole_id());
        return savedUserDto;
    }

    //??????????????????????????????
    public List<UserResponseDto> getAllUsersWithRole() {
        return userRepository.findAll().stream()
                .map(user -> new UserResponseDto(
                        user.getId(),
                        user.getName(),
                        user.getEmail(),
                        user.getPassword(),
                        user.getRole().getRole_id()
                ))
                .collect(Collectors.toList());
    }

    // delete user
    public void softDeleteUser(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setIsDeleted(true);  // Mark the user as deleted
        userRepository.save(user);
    }
    //update user

    public UserUpDataDto updateUser(Integer userId, UserUpDataDto userUpDataDto) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Update fields if provided
        if (userUpDataDto.getName() != null) {
            user.setName(userUpDataDto.getName());
        }
        if (userUpDataDto.getEmail() != null) {
            user.setEmail(userUpDataDto.getEmail());
        }
        if (userUpDataDto.getPassword() != null) {
            user.setPassword(userUpDataDto.getPassword());
        }
        if (userUpDataDto.getRoleId() != null) {
            Role role = roleRepository.findById(userUpDataDto.getRoleId())
                    .orElseThrow(() -> new RuntimeException("Role not found"));
            user.setRole(role);
        }
        // map user entity to userDto
        user = userRepository.save(user);// Save user

        UserUpDataDto updateUser=new UserUpDataDto();
        updateUser.setName(user.getName());
        updateUser.setEmail(user.getEmail());
        updateUser.setPassword(user.getPassword());
        updateUser.setRoleId(user.getRole().getRole_id());
        return updateUser;
    }
    //??????????????????????????????
    public List<UserResponseDto> getAllActiveUsers() {
        return userRepository.findAllActiveUsers().stream()
                .map(user->new UserResponseDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getRole().getRole_id()
        )).collect(Collectors.toList()); // Fetch non-deleted users
    }

    ///////////////////////////////////////////////////////////////////////////////////

// method for  project within admin (crete project )
    public ProjectDto createProject(ProjectDto projectDto){
        Project project=new Project();
        project.setProjectName(projectDto.getProjectName());
        project.setStart_date(projectDto.getStart_date());
        project.setEnd_date(projectDto.getEnd_date());
        project.setDescription(projectDto.getDescription());
        // Fetch the project manager by ID and set it
        User projectManager =userRepository.findById(projectDto.getProjectManagerId())
                .orElseThrow(()-> new RuntimeException("Project manager not found"));
        project.setProjectManager(projectManager);

        project=projectRepository.save(project);  // Save and return the new project

        // convert Project entity to Dto
        ProjectDto projectDto1=new ProjectDto();
        projectDto1.setProject_id(project.getProject_id());
        projectDto1.setProjectName(project.getProjectName());
        projectDto1.setDescription(project.getDescription());
        projectDto1.setStart_date(project.getStart_date());
        projectDto1.setEnd_date(project.getEnd_date());
        projectDto1.setProjectManagerId(project.getProjectManager().getId());
        return projectDto1;
    }
    public List<ProjectManagerDto> getAllActiveProjectManagers() {
        List<Object[]> results = userRepository.findAllActiveProjectManagers();
        return results.stream()
                .map(result -> new ProjectManagerDto((Integer) result[0], (String) result[1])) // Cast to correct types
                .collect(Collectors.toList());
    }
    public void softDeleteProject(Integer id){
        Project project=projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        project.setIsDeleted(true);
        projectRepository.save(project);
    }
    public ProjectUpDateDto updateProject(Integer projectId, ProjectUpDateDto projectUpDateDto) {

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        // Update fields if provided
        if (projectUpDateDto.getProjectName() != null) {
            project.setProjectName(projectUpDateDto.getProjectName());
        }
        if (projectUpDateDto.getStart_date() != null) {
            project.setStart_date(projectUpDateDto.getStart_date());
        }
        if (projectUpDateDto.getEnd_date() != null) {
            project.setEnd_date(projectUpDateDto.getEnd_date());
        }
        if (projectUpDateDto.getDescription() != null) {
            project.setDescription(projectUpDateDto.getDescription());
        }
        if (projectUpDateDto.getProjectManagerId() != null) {
            User projectManager = userRepository.findById(projectUpDateDto.getProjectManagerId())
                    .orElseThrow(() -> new RuntimeException("Role not found"));
            project.setProjectManager(projectManager);
        }
        // map user entity to userDto
        project= projectRepository.save(project);// Save user

        ProjectUpDateDto updateProject=new ProjectUpDateDto();
        updateProject.setProjectName(project.getProjectName());
        updateProject.setStart_date(project.getStart_date());
        updateProject.setEnd_date(project.getEnd_date());
        updateProject.setDescription(project.getDescription());
        updateProject.setProjectManagerId(project.getProjectManager().getId());
        return updateProject;
    }



    public List<ProjectResponseDto> getAllActiveProjects(){
        return projectRepository.findAllActiveProjects().stream()
                .map(project -> new ProjectResponseDto(
                        project.getProject_id(),
                        project.getProjectName(),
                        project.getProjectManager() != null ? project.getProjectManager().getName() : "No Manager Assigned",
                        project.getStart_date(),
                        project.getEnd_date(),
                        project.getDescription(),
                        project.getAssignedUsers()
                )).collect(Collectors.toList());

    }

}

