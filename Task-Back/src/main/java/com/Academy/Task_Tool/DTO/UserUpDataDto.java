package com.Academy.Task_Tool.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserUpDataDto {

    private String name;
    private String email;
    private String password;
    private Integer roleId;

}
