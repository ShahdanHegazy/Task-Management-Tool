package com.Academy.Task_Tool.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ProjectResponseDto {

    private String projectName;
    private String projectManagerName;
    private LocalDateTime start_date;
    private LocalDateTime end_date;
    private String description;

}
