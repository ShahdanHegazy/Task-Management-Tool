package com.Academy.Task_Tool.DTO;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor

public class ProjectDto {

    private int project_id;
    private String projectName;
    private String description;
    private LocalDateTime start_date;
    private LocalDateTime end_date;
    private Integer projectManagerId;

}
