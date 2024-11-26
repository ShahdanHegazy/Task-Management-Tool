package com.Academy.Task_Tool.DTO;

import com.Academy.Task_Tool.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectResponseDto {
    private Integer project_id;
    private String projectName;
    private String projectManager;
    private LocalDate start_date;
    private LocalDate end_date;
    private String description;
    private List<User> assignedUsers;
}
