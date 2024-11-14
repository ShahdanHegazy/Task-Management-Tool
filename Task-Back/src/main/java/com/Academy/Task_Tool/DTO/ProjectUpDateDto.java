package com.Academy.Task_Tool.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectUpDateDto {
private String projectName;
private LocalDateTime start_date;
private LocalDateTime end_date;
private String description;
private Integer projectManagerId ;
}