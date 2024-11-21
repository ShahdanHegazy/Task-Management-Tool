package com.Academy.Task_Tool.Services;
import com.Academy.Task_Tool.DTO.CardDto;
import com.Academy.Task_Tool.DTO.CommentDto;
import com.Academy.Task_Tool.DTO.ProjectMemberAssignmentDto;
import com.Academy.Task_Tool.Entity.Card;
import com.Academy.Task_Tool.Entity.Comment;
import com.Academy.Task_Tool.Entity.Project;
import com.Academy.Task_Tool.Entity.User;
import com.Academy.Task_Tool.Repository.CardRepository;
import com.Academy.Task_Tool.Repository.CommentRepository;
import com.Academy.Task_Tool.Repository.ProjectRepository;
import com.Academy.Task_Tool.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProjectManagerService {
    
    @Autowired
    private CardRepository cardRepository;

//    public List<CardDto> getCardsByProjectId(Integer projectId) {
//        return cardRepository.findByProjectIdAndIsDeletedFalse(projectId);
//    }

//    GET ALL CARD
    public CardDto getCardById(Integer cardId) {
        Card card = cardRepository.findByCardId(cardId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
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


    public Project assignUserToProject(ProjectMemberAssignmentDto dto) {
        Project project = projectRepository.findById(dto.getProjectId())
                .orElseThrow(() -> new RuntimeException("Project not found with ID: " + dto.getProjectId()));

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + dto.getUserId()));

        project.getAssignedUsers().add(user);

        return projectRepository.save(project);
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
