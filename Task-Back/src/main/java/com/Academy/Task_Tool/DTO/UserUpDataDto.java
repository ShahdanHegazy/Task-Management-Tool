package com.Academy.Task_Tool.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserUpDataDto {
    private String name;
    private String email;
    private Integer roleId;

}
