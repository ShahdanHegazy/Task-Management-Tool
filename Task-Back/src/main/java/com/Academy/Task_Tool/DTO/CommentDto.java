package com.Academy.Task_Tool.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class CommentDto {
    private String content;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Integer cardId;
    private boolean deleted;


    public void setUpdatedAt(String updateAt) {
    }
}
