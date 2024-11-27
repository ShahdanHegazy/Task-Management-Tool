package com.Academy.Task_Tool.Services;
import com.Academy.Task_Tool.DTO.*;
import com.Academy.Task_Tool.Entity.Card;
import com.Academy.Task_Tool.Entity.Comment;
import com.Academy.Task_Tool.Entity.Project;
import com.Academy.Task_Tool.Entity.User;
import com.Academy.Task_Tool.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProjectManagerService {
    
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ListRepository listRepository;
    @Autowired
    private EmailService emailService;


    //    GET ALL CARD
    public CardDto getCardById(Integer cardId) {
        Card card = cardRepository.findByCardId(cardId)
                .orElseThrow(() -> new RuntimeException("Card not found"));
        return convertToDTO(card);
    }

//    CREAT card
public CardDto createCard(CardDto cardDto) {
    Card card = convertToEntity(cardDto);
    return convertToDTO(cardRepository.save(card));
}

//  UPDATE CARD
public CardDto updateCard(Integer cardId, CardDto cardDto) {
    Card task = cardRepository.findById(cardId)
            .orElseThrow(() -> new RuntimeException("Card not found"));
    task.setTitle(cardDto.getTitle());
    task.setDescription(cardDto.getDescription());
    task.setDueDate(cardDto.getDueDate());
    task.setPriority(cardDto.getPriority());
    return convertToDTO(cardRepository.save(task));
}

//  SOFT DELETE FOR CARD
public void deleteCard(Integer cardId) {
    Card card = cardRepository.findById(cardId)
            .orElseThrow(() -> new RuntimeException("Card not found"));
    card.setIsDeleted(true); // Soft delete
    cardRepository.save(card);
}

//  ASSIGN MEMBER TO PROJECT
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

//    public Project assignUsersToProject(ProjectMemberAssignmentDto dto) {
//        Project project = projectRepository.findById(dto.getProjectId())
//                .orElseThrow(() -> new RuntimeException("Project not found with ID: " + dto.getProjectId()));
//        List<User> users = userRepository.findAllById(dto.getUserIds());
//        if (users.size() != dto.getUserIds().size()) {
//            throw new RuntimeException("One or more users not found with the provided IDs");
//        }
//        for (User user : users) {
//            if(project.getAssignedUsers().isEmpty())
//            {
//                project.getAssignedUsers().add(user);
//            }
//            else {
//                project.getAssignedUsers().clear();
//                project.getAssignedUsers().add(user);
//            }
//            user.getAssignedProjects().add(project);
//            // Send an email notification
//            String subject = "You Have Been Assigned to a Project";
//            String body = "Dear " + user.getName() + ",\n\n" +
//                    "You have been assigned to the project: " + project.getProjectName() + ".\n\n" +
//                    "Regards,\nProject Management Team";
//            emailService.sendEmail(user.getEmail(), subject, body);
//        }
//        userRepository.saveAll(users);
//        projectRepository.save(project);
//        return project;
//    }
public Project assignUsersToProject(ProjectMemberAssignmentDto dto) {
    Project project = projectRepository.findById(dto.getProjectId())
            .orElseThrow(() -> new RuntimeException("Project not found with ID: " + dto.getProjectId()));

    // Fetch all users from the provided user IDs
    List<User> users = userRepository.findAllById(dto.getUserIds());
    if (users.size() != dto.getUserIds().size()) {
        throw new RuntimeException("One or more users not found with the provided IDs");
    }

    // Clear the existing list of assigned users
    project.getAssignedUsers().clear();

    // Add the new list of users to the project
    project.getAssignedUsers().addAll(users);

    // Notify all new users via email and update their project assignments
    for (User user : users) {
        user.getAssignedProjects().add(project);

        // Send an email notification
        String subject = "You Have Been Assigned to a Project";
        String body = "Dear " + user.getName() + ",\n\n" +
                "You have been assigned to the project: " + project.getProjectName() + ".\n\n" +
                "Regards,\nTickTask Team";
        emailService.sendEmail(user.getEmail(), subject, body);
    }

    // Save changes to the database
    projectRepository.save(project);
    userRepository.saveAll(users);

    return project;
}

    public List<UserDto> getMembersByProjectId(Integer projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found with ID: " + projectId));
        List<UserDto> assignedUsers = project.getAssignedUsers()
                .stream()
                .map(user -> {
                    UserDto userDto = new UserDto();
                    userDto.setId(user.getId());
                    userDto.setName(user.getName());
                    userDto.setEmail(user.getEmail());
                    return userDto;
                })
                .collect(Collectors.toList());
        return assignedUsers;
    }


    public List<ListAllMembersDto> getAllMembers(Integer roleId) {
        roleId=3;

        List<User> users=userRepository.findByRoleIDAndIsDeletedFalse(3);
        List<ListAllMembersDto> listAllMembersDto=users.stream()
                .map(user -> {
                    ListAllMembersDto userDto = new ListAllMembersDto();
                    userDto.setUserId(user.getId());
                    userDto.setName(user.getName());
                    return userDto;
                })
                .collect(Collectors.toList());
        return listAllMembersDto;
    }


    private CardDto convertToDTO(Card card) {
        CardDto cardDto = new CardDto();
        cardDto.setCardId(card.getCardId());
        cardDto.setPriority(card.getPriority());
        cardDto.setCardId(card.getCardId());
        cardDto.setDescription(card.getDescription());
        cardDto.setTitle(card.getTitle());
        cardDto.setCreateBy(String.valueOf(card.getCreatedBy()));
        cardDto.setAssignedTo(String.valueOf(card.getAssignedTo()));
        cardDto.setDueDate(card.getDueDate());
        cardDto.setCreateAt(card.getCreateAt());
        return cardDto;
    }
    private Card convertToEntity(CardDto cardDto) {
        Card card = new Card();
        card.setCardId(cardDto.getCardId());
        card.setPriority(cardDto.getPriority());
        card.setCardId(cardDto.getCardId());
        card.setDescription(cardDto.getDescription());
        card.setTitle(cardDto.getTitle());
        card.setCreatedBy(String.valueOf(cardDto.getCreateBy()));
        card.setAssignedTo(String.valueOf(cardDto.getAssignedTo()));
        card.setDueDate(cardDto.getDueDate());
        card.setCreateAt(cardDto.getCreateAt());
        return card;
    }

//    CRUD For Comment

    @Autowired
    private CommentRepository commentRepository;


    // Retrieve all comments for a specific task
    public List<CommentDto> getCommentsByCardId(Integer cardId) {
        return commentRepository.findByCardCardIdAndIsDeletedFalse(cardId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Retrieve a specific comment by commentId and cardId
    public CommentDto getCommentByIdAndCardId(Integer commentId, Integer cardId) {
        Comment comment = commentRepository.findByCommentidAndCardCardIdAndIsDeletedFalse(commentId, cardId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
        return convertToDTO(comment);
    }

    // Create a new comment for a card
    public CommentDto createComment(Integer cardId, CommentDto commentDto) {
        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new RuntimeException("Card not found"));
        Comment comment = convertToEntity(commentDto);
        comment.setContent(comment.getContent());
        comment.setUpdate_at(comment.getUpdate_at());
        comment.setCard(card);
        comment.setCreate_at(new Timestamp(System.currentTimeMillis()));
        comment.setIsDeleted(false);

        return convertToDTO(commentRepository.save(comment));
    }

    // Update an existing comment for a card
    public CommentDto updateComment(Integer commentId, Integer cardId, CommentDto commentDTO) {
        Comment comment = commentRepository.findByCommentidAndCardCardIdAndIsDeletedFalse(commentId, cardId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
        comment.setContent(commentDTO.getContent());
        comment.setUpdate_at(new Timestamp(System.currentTimeMillis()).toString());

        return convertToDTO(commentRepository.save(comment));
    }

    // Soft delete a comment by commentId and cardId
    public void deleteComment(Integer commentId, Integer cardId) {
        Comment comment = commentRepository.findByCommentidAndCardCardIdAndIsDeletedFalse(commentId, cardId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
        comment.setIsDeleted(true);
        commentRepository.save(comment);
    }

    // Convert Entity to DTO
    private CommentDto convertToDTO(Comment comment) {
        CommentDto dto = new CommentDto();
        dto.setContent(comment.getContent());
        dto.setCreatedAt(comment.getCreate_at());
        dto.setUpdatedAt(comment.getUpdate_at());
        dto.setCardId(comment.getCard().getCardId());
        dto.setDeleted(comment.getIsDeleted());
        return dto;
    }

    // Convert DTO to Entity
    private Comment convertToEntity(CommentDto commentDTO) {
        Comment comment = new Comment();
        comment.setContent(commentDTO.getContent());
        comment.setIsDeleted(commentDTO.isDeleted());
        return comment;
    }



}
