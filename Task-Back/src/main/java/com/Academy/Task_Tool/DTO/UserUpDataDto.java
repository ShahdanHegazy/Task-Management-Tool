package com.Academy.Task_Tool.DTO;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpDataDto {

    private String name;
    private String email;
    private String password;
    private Integer roleId;

}
