package com.Academy.Task_Tool.Services;
import com.Academy.Task_Tool.DTO.*;
import com.Academy.Task_Tool.Entity.Card;
import com.Academy.Task_Tool.Entity.Comment;
import com.Academy.Task_Tool.Entity.Project;
import com.Academy.Task_Tool.Entity.User;
import com.Academy.Task_Tool.GolbalException.ProjectNotFoundException;
import com.Academy.Task_Tool.Repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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
    @Autowired
    private ProjectRepository projectRepository;

    //    GET ALL CARD
    public CardDto getCardById(Integer cardId) {
        Card card = cardRepository.findByCardId(cardId)
                .orElseThrow(() -> new RuntimeException("Card not found"));
        return convertToDTO(card);
    }

//    CREAT card
public CardDto createCard(CardDto cardDto, Integer projectId, String listName) {
    Card card = convertToEntity(cardDto);
    card.setProject(projectRepository.findById(projectId).orElseThrow(() -> new RuntimeException("Project not found")));
    card.setList(listRepository.findByProjectAndListName(projectRepository.findById(projectId),listName));
    return convertToDTO(cardRepository.save(card));
}

    public CardDto updateCard(Integer cardId, CardDto cardDto) {
        // Fetch the card by its ID
        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new RuntimeException("Card not found"));

        // Update basic fields
        card.setTitle(cardDto.getTitle());
        card.setDescription(cardDto.getDescription());
        card.setCreateAt(cardDto.getCreateAt());
        card.setDueDate(cardDto.getDueDate());
        card.setPriority(cardDto.getPriority());
        card.setAssignedTo(cardDto.getAssignedTo());

        // Save the updated card and return the DTO
        Card updatedCard = cardRepository.save(card);
        return convertToDTO(updatedCard);
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
    private UserRepository userRepository;


public Project assignUsersToProject(ProjectMemberAssignmentDto dto) {
    Project project = projectRepository.findById(dto.getProjectId())
            .orElseThrow(() -> new RuntimeException("Project not found with ID: " + dto.getProjectId()));
    List<User> users = userRepository.findAllById(dto.getUserIds());
    if (users.size() != dto.getUserIds().size()) {
        throw new RuntimeException("One or more users not found with the provided IDs");
    }
    project.getAssignedUsers().clear();
    project.getAssignedUsers().addAll(users);
    for (User user : users) {
        user.getAssignedProjects().add(project);
        String subject = "You Have Been Assigned to a Project";
        String body = "Dear " + user.getName() + ",\n\n" +
                "You have been assigned to the project: " + project.getProjectName() + ".\n\n" +
                "Regards,\nTickTask Team";
        emailService.sendEmail(user.getEmail(), subject, body);
    }
    projectRepository.save(project);
    userRepository.saveAll(users);
    return project;
}


    public List<UserDto> getMembersByProjectId(Integer projectId) {
        Optional<Project> project = projectRepository.findById(projectId);
        if(project.get().getIsDeleted()){
            throw new ProjectNotFoundException("Project is deleted");
        }
        List<User> users = projectRepository.findAllUsersByProjectId(projectId);

                return users.stream()
                .map(user -> {
                    UserDto userDto = new UserDto();
                    userDto.setId(user.getId());
                    userDto.setName(user.getName());
                    userDto.setEmail(user.getEmail());
                    return userDto;
                })
                .collect(Collectors.toList());

    }

    @Transactional
    public void removeUserFromProject(Integer projectId, Integer userId) {
        if (projectRepository == null) {
            throw new IllegalStateException("ProjectRepository is not initialized");
        }
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        project.getAssignedUsers().remove(user);
        user.getAssignedProjects().remove(project);

        projectRepository.save(project);
        userRepository.save(user);
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
        cardDto.setTitle(card.getTitle());
        cardDto.setDescription(card.getDescription());
        cardDto.setPriority(card.getPriority());
        cardDto.setCreateAt(card.getCreateAt());
        cardDto.setDueDate(card.getDueDate());
        cardDto.setAssignedTo(card.getAssignedTo() != null ? card.getAssignedTo().getId() : null); // Convert User to ID
        return cardDto;
    }

    private Card convertToEntity(CardDto cardDto) {
        Card card = new Card();
        card.setCardId(cardDto.getCardId());
        card.setPriority(cardDto.getPriority());
        card.setCardId(cardDto.getCardId());
        card.setDescription(cardDto.getDescription());
        card.setTitle(cardDto.getTitle());
        card.setAssignedTo(cardDto.getAssignedTo());
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
