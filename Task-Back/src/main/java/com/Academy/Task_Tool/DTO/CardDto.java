package com.Academy.Task_Tool.DTO;

import com.Academy.Task_Tool.Entity.User;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDate;


@Data
public class CardDto {

    private Integer cardId;
    private String title;
    private LocalDate dueDate;
    private String description;
    private String  priority;
    private LocalDate createAt;
    private Integer createBy;
    private Integer assignedTo;

}
