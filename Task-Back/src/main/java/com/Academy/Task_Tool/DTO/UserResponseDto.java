package com.Academy.Task_Tool.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {
    private String name;
    private String email;
    private Integer roleId;

    public UserResponseDto(String name,String email, Integer roleId){
        this.name=name;
        this.email=email;
        this.roleId=roleId;
    }
}
