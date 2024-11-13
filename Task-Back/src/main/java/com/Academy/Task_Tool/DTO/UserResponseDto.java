package com.Academy.Task_Tool.DTO;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private Integer id;
    private String name;
    private String email;
    private Integer roleId;

}
