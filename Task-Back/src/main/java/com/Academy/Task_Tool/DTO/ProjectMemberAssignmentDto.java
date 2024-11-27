package com.Academy.Task_Tool.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class ProjectMemberAssignmentDto {

        private Integer projectId;
        private List<Integer> userIds;


//    public Iterable<Integer> getUserIds() {
//    }
}
