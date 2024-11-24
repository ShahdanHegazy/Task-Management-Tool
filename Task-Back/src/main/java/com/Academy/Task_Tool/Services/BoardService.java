package com.Academy.Task_Tool.Services;

import com.Academy.Task_Tool.DTO.*;
import com.Academy.Task_Tool.Entity.*;
import com.Academy.Task_Tool.Repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.Academy.Task_Tool.Entity.List;

import java.util.ArrayList;
import java.util.Set;
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

    public java.util.List<ProjectDto> getAllAssignedProjects(int userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("User not found")
        );
        java.util.List<Project> projects = user.getAssignedProjects();

        java.util.List<ProjectDto> projectDTOs = projects.stream()
                .map(project -> {
                    ProjectDto projectDTO = new ProjectDto();
                    projectDTO.setProject_id(project.getProject_id());
                    projectDTO.setProjectName(project.getProjectName());
                    return projectDTO;
                })
                .collect(Collectors.toList());

        return projectDTOs;
    }




    // move card from list to another

    public String moveCard(int cardId, int sourceListId, int targetListId) {
        Card card = cardRepository.findById(cardId).orElseThrow(() -> new EntityNotFoundException("Card not found"));
        com.Academy.Task_Tool.Entity.List sourceList = listRepository.findById(sourceListId).orElseThrow(() -> new EntityNotFoundException("Source list not found"));
        com.Academy.Task_Tool.Entity.List targetList = listRepository.findById(targetListId).orElseThrow(() -> new EntityNotFoundException("Target list not found"));
        // Remove card from source list
        sourceList.getCards().remove(card);

        // Add card to destination list
        targetList.getCards().add(card);
        card.setList(targetList);

        // Save changes
        cardRepository.save(card);
        listRepository.save(sourceList);
        listRepository.save(targetList);
        return "changed succeddfully";
    }


    //get board with lists and cards

    public java.util.List<BoardDto> getBoard(int projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new EntityNotFoundException("Project not found"));


        java.util.List<BoardDto> lists = project.getLists().stream()
                .map((com.Academy.Task_Tool.Entity.List list ) -> {
                    BoardDto boardDto =new BoardDto();
                    boardDto.setId(list.getList_id());
                    boardDto.setName(list.getList_name());
                    if (!list.getCards().isEmpty()) {
                        boardDto.setCardList(list.getCards().stream()
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
    public String addList(ListDto listDto, int projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new EntityNotFoundException("Project not found"));
        List list =new List();
        list.setList_name(listDto.getList_name());
        list.setProject(project);
        listRepository.save(list);
        return "added successfully";
    }


    public String updateList( ListDto listUpdateDto , int listId) {
        List list = listRepository.findById(listId).orElseThrow(() -> new EntityNotFoundException("List not found"));
        list.setList_name(listUpdateDto.getList_name());
        listRepository.save(list);
        return "updated successfully";
    }

    public String softDeleteList(int listId) {
        List list = listRepository.findById(listId)
                .orElseThrow(() -> new EntityNotFoundException("List not found"));
        list.setIsDeleted(true);
        listRepository.save(list);
        return "deleted successfully";
    }
}
