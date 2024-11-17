package com.Academy.Task_Tool.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectUpDateDto {
private String projectName;
private LocalDate start_date;
private LocalDate end_date;
private String description;
private Integer projectManagerId ;
}
