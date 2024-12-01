package com.Academy.Task_Tool.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RemoveUserResponseDto {
    private String message;
    private Integer projectId;
    private Integer userId;
}

