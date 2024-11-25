package com.Academy.Task_Tool.DTO;

import com.Academy.Task_Tool.Entity.List;
import com.Academy.Task_Tool.Entity.User;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ProjectBoardDto {
    private Integer project_id;
    private String project_name;
    private String description;
    private LocalDate start_date;
    private LocalDate end_date;
    private java.util.List<User> assignedUsers;
    private java.util.List<BoardDto> lists ;
}
