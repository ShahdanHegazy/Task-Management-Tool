package com.Academy.Task_Tool.DTO;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDto {
    private Integer project_id;
    private String projectName;
    private String description;
    private LocalDate start_date;
    private LocalDate end_date;
    private Integer projectManagerId;
}
