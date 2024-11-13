package com.Academy.Task_Tool.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ProjectResponseDto {

    private String projectTitle;
    private String projectManagerName;
    private LocalDateTime start_date;
    private LocalDateTime end_date;
    private String description;

}
