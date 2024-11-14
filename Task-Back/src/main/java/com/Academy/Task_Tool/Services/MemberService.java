package com.Academy.Task_Tool.Services;

import com.Academy.Task_Tool.DTO.ProjectDto;
import com.Academy.Task_Tool.DTO.CardDto;
import com.Academy.Task_Tool.Entity.*;
import com.Academy.Task_Tool.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MemberService {
    
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
    public ResponseEntity<List<ProjectDto>> getAllAssignedProjects(int userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("User not found")
        );
        Set<Project> projects = user.getAssignedProjects();
        List<ProjectDto> projectDTOs = projects.stream()
                .map(project -> {
                    ProjectDto projectDTO = new ProjectDto();
                    projectDTO.setProject_id(project.getProject_id());
                    projectDTO.setProjectName(project.getProjectName());
                    return projectDTO;
                })
                .collect(Collectors.toList());

        return new ResponseEntity<>(projectDTOs, HttpStatus.OK);
    }


    //get all tasks
    public ResponseEntity<List<CardDto>> getProjectWithTasks(int projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow(
                ()->new RuntimeException("Project not found")
        );
        Set<Card> cards = project.getAssignedCards();
        List<CardDto> cardDtos = cards.stream()
                .map(
                        task -> {
                            CardDto taskDTO = new CardDto();
                            taskDTO.setCardId(task.getCardId());
                            taskDTO.setTitle(task.getTitle());
                            taskDTO.setDescription(task.getDescription());
                            taskDTO.setPriority(task.getPriority());
                            taskDTO.setPriority(task.getPriority());
                            taskDTO.setDueDate(task.getDueDate());
                            taskDTO.setCreateAt(task.getCreateAt());
                            taskDTO.setAssignedTo(String.valueOf(task.getAssignedTo()));
                            return taskDTO;
                        }).collect(Collectors.toList());

        return new ResponseEntity<>(cardDtos, HttpStatus.OK);
    }

    public ResponseEntity<String> moveTask(int taskId, int sourceListId, int targetListId) {

               /* Card card = cardRepository.findById(cardId)
                        .orElseThrow(() -> new EntityNotFoundException("Card not found"));

                List sourceList = listRepository.findById(sourceListId)
                        .orElseThrow(() -> new EntityNotFoundException("Source list not found"));

                List destinationList = listRepository.findById(destinationListId)
                        .orElseThrow(() -> new EntityNotFoundException("Destination list not found"));

                // Remove card from source list
                sourceList.getCards().remove(card);

                // Add card to destination list
                destinationList.getCards().add(card);
                card.setList(destinationList);

                // Update card order in both lists (optional, depending on your implementation)
                // ...

                // Save changes
                cardRepository.save(card);
                listRepository.save(sourceList);
                listRepository.save(destinationList);

        }*/
        return null ;
    }

    // update task status



//    //get task
//    public ResponseEntity<Card> getCard(int projectId, int taskId) {
//         cardRepository.findById(taskId);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    //create comment
//    public ResponseEntity<Comment> createComment(Comment comment ,int projectId, int taskId) {
//        commentRepository.save(comment);
//        return new ResponseEntity<>(comment, HttpStatus.CREATED);
//    }
//
//    // update comment
//    public ResponseEntity<Comment> updateComment(Comment comment , int projectId, int taskId, int commentId) {
//        Comment updated = commentRepository.findById(commentId).orElseThrow();
//        updated.setContent(comment.getContent());
//        updated.setUpdate_at(comment.getUpdate_at());
//        return new ResponseEntity<>(updated, HttpStatus.OK);
//    }
//
//    // delete comment
//
//        public ResponseEntity<String> deleteComment(int projectId, int taskId , int commentId) {
//        commentRepository.deleteById(commentId);
//        return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
//    }


}
