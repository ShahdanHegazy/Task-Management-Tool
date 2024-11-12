package com.Academy.Task_Tool.Services;

import com.Academy.Task_Tool.DTO.*;
import com.Academy.Task_Tool.Entity.Project;
import com.Academy.Task_Tool.Entity.Role;
import com.Academy.Task_Tool.Entity.User;
import com.Academy.Task_Tool.Repository.ProjectRepository;
import com.Academy.Task_Tool.Repository.RoleRepository;
import com.Academy.Task_Tool.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    private Object role_id;
//    @Autowired
//    private final UserRepsitory userRepository;

    public Integer getProjectCount() {
        return projectRepository.countAllProject();
    }




    // GET endpoint to fetch user count by role_id
    @Autowired
    public AdminService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public long getUserCountByRoleId(Integer roleId) {
        return userRepository.countUsersByRoleId(roleId);
    }

/////////////////////////////////////////////////////////////////

    // method for create user within admin
    public UserDto createUser(UserDto userDto) {
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

    public List<UserResponseDto> getAllUsersWithRole() {
        return userRepository.findAll().stream()
                .map(user -> new UserResponseDto(
                        user.getId(),
                        user.getName(),
                        user.getEmail(),
                        user.getRole().getRole_id()
                ))
                .collect(Collectors.toList());
    }

    public User softDeleteUser(Integer id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setIsDeleted(true);  // Mark the user as deleted
        userRepository.save(user); // Save the updated user

        return user;
    }

    public List<UserResponseDto> getAllActiveUsers() {
        return userRepository.findAllActiveUsers().stream()
                .map(user->new UserResponseDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole().getRole_id()
        )).collect(Collectors.toList()); // Fetch non-deleted users
    }

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

    ///////////////////////////////////////////////////////////////////////////////////

// method for create new project
    public Project createProject(ProjectDto projectDto){
        Project project=new Project();
        project.setProjectName(projectDto.getProjectName());
        project.setStart_date(projectDto.getStart_date());
        project.setEnd_date(projectDto.getEnd_date());
        project.setDescription(projectDto.getDescription());

        // Fetch the project manager by ID and set it

        User projectManager =userRepository.findById(projectDto.getProjectManagerId())
                .orElseThrow(()-> new RuntimeException("Project manager not found"));
        project.setProjectManager(projectManager);

        return projectRepository.save(project);  // Save and return the new project
    }

    // method for retrieve project Manager
    public List <ProjectManagerDto> getAllProjectManager(){
        // Fetch all users and map them to ProjectManagerDto
        return userRepository.findAll().stream()
                .map(user -> new ProjectManagerDto(user.getId(), user.getName()))
                .collect(Collectors.toList());
    }



    //method for retrieve details of project by id ;

//    public ProjectDto getAllDetailsProject( Integer id){
//        return projectRepository.findById(id)
//                .map(project -> new ProjectDto(
//                        project.getProject_id(),
//                        project.getProjectName(),
//                        project.getProjectManager() != null ? project.getProjectManager().getName() : "No Manager",
//                        project.getStart_date(),
//                        project.getEnd_date(),
//                        project.getDescription()
//                ))
//                .orElseThrow(() -> new RuntimeException("Project not found with id: " + id));
//    }


    // method for update data of project
//    public Project updateProject(Integer id,){
//
//    }

}
