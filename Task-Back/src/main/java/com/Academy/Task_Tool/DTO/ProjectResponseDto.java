package com.Academy.Task_Tool.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectResponseDto {
    private Integer project_id;
    private String projectName;
    private String projectManagerName;
    private LocalDateTime start_date;
    private LocalDateTime end_date;
    private String description;

}
