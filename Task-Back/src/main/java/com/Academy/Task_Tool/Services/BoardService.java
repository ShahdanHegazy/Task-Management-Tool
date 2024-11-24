package com.Academy.Task_Tool.Services;

import com.Academy.Task_Tool.DTO.*;
import com.Academy.Task_Tool.Entity.*;
import com.Academy.Task_Tool.Repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Academy.Task_Tool.Entity.List;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class BoardService {
    
    @Autowired
    CardRepository cardRepository;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    ListRepository listRepository;

//get all assigned projects

    public java.util.List<AssignedProjDto> getAllAssignedProjects(int userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("User not found")
        );
        java.util.List<Project> projects = java.util.List.of();
        if (user.getRole().getRole_id()==3) {
            projects=projectRepository.findAssignedProjectsByUserId(userId);
        }


        java.util.List<AssignedProjDto> projectDTOs = projects.stream()
                .map(project -> {
                    AssignedProjDto projectDTO = new AssignedProjDto();
                    projectDTO.setProject_id(project.getProject_id());
                    projectDTO.setProject_name(project.getProjectName());
                    projectDTO.setProject_desc(project.getDescription());
                    return projectDTO;
                })
                .collect(Collectors.toList());

        return projectDTOs;
    }
    public java.util.List<AssignedProjDto> getAllManagedProjects(int userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("User not found")
        );

        java.util.List<Project> projects = java.util.List.of();
        if (user.getRole().getRole_id()==2) {
            projects = projectRepository.findManagedProjectsByUserId(userId);
        }
        java.util.List<AssignedProjDto> projectDTOs = projects.stream()
                .map(project -> {
                    AssignedProjDto projectDTO = new AssignedProjDto();
                    projectDTO.setProject_id(project.getProject_id());
                    projectDTO.setProject_name(project.getProjectName());
                    projectDTO.setProject_desc(project.getDescription());
                    return projectDTO;
                })
                .collect(Collectors.toList());

        return projectDTOs;


    }


    // move card from list to another

    public String moveCard(int cardId, int sourceListId, int targetListId) {
        Card card = cardRepository.findByCardIdAndIsDeletedFalse(cardId).orElseThrow(() -> new EntityNotFoundException("Card not found"));
        List sourceList = listRepository.findByListIdAndIsDeletedFalse(sourceListId).orElseThrow(() -> new EntityNotFoundException("Source list not found"));
        List targetList = listRepository.findByListIdAndIsDeletedFalse(targetListId).orElseThrow(() -> new EntityNotFoundException("Target list not found"));
        // Remove card from source list
        sourceList.getCards().remove(card);

        // Add card to destination list
        targetList.getCards().add(card);
        card.setList(targetList);

        // Save changes
        cardRepository.save(card);
        listRepository.save(sourceList);
        listRepository.save(targetList);
        return "Card moved succeddfully";
    }



    //get board with lists and cards

    public java.util.List<BoardDto> getBoard(int projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new EntityNotFoundException("Project not found"));


        java.util.List<BoardDto> lists = listRepository.findAllByProjectIdAndIsDeletedFalse(projectId).stream()
                .map(list -> {
                    BoardDto boardDto =new BoardDto();
                    boardDto.setId(list.getListId());
                    boardDto.setName(list.getListName());
                    if (!list.getCards().isEmpty()) {
                        boardDto.setCardList(cardRepository.findAllByListIdAndIsDeletedFalse(list.getListId()).stream()
                                .map(card -> {
                                    CardBoardDto cardDto = new CardBoardDto();
                                    cardDto.setCardId(card.getCardId());
                                    cardDto.setTitle(card.getTitle());
                                    return cardDto;
                                }).collect(Collectors.toList()));
                    } else {
                        boardDto.setCardList(new ArrayList<>());
                    }

                    return boardDto;

                })
                .collect(Collectors.toList());

        return lists;
    }

    // create list
    public ListDto addList(ListDto listDto, int projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new EntityNotFoundException("Project not found"));
        List list =new List();
        list.setListName(listDto.getList_name());
        list.setCreatedAt(LocalDateTime.now());
        list.setProject(project);
        listRepository.save(list);
        return listDto;
    }


    public ListDto updateList( ListDto listUpdateDto , int listId) {
        List list = listRepository.findById(listId).orElseThrow(() -> new EntityNotFoundException("List not found"));
        list.setListName(listUpdateDto.getList_name());
        listRepository.save(list);
        return listUpdateDto;
    }

    public String softDeleteList(int listId) {
        List list = listRepository.findById(listId)
                .orElseThrow(() -> new EntityNotFoundException("List not found"));
        list.setIsDeleted(true);
        listRepository.save(list);
        return "deleted successfully";
    }


}
