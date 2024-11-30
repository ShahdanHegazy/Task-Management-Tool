package com.Academy.Task_Tool.DTO;

import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDate;

@Data
public class CardBoardDto {
    private Integer cardId;
    private String title;
    private LocalDate dueDate;
    private String description;
    private String  priority;
    private LocalDate createAt;
    private Integer createBy;
    private Integer assignedTo;

}
