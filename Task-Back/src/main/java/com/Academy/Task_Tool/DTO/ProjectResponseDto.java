package com.Academy.Task_Tool.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectResponseDto {
    private Integer project_id;
    private String projectName;
    private String projectManagerName;
    private LocalDate start_date;
    private LocalDate end_date;
    private String description;

}
