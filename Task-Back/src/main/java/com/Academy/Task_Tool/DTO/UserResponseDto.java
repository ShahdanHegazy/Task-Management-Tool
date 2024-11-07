package com.Academy.Task_Tool.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {
    private String name;
    private String email;
    private String role;

    public UserResponseDto(String name,String email, String role){
        this.name=name;
        this.email=email;
        this.role=role;
    }
}
