package com.Academy.Task_Tool.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignedProjDto {
    private Integer project_id;
    private String project_name;
    private String project_desc;
}
