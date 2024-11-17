package com.Academy.Task_Tool.DTO;

import com.Academy.Task_Tool.Entity.Project;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
public class ListDto {
   private String list_name;
   private Project project;
}
