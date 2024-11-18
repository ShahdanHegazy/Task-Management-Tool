package com.Academy.Task_Tool.DTO;

import lombok.Data;

import java.sql.Timestamp;


@Data
public class CardDto {
    private Integer cardId;
    private String title;
    private Timestamp dueDate;
    private String description;
    private String  priority;
    private Timestamp createAt;
    private String createBy;
    private String assignedTo;

}
