package com.Academy.Task_Tool.DTO;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String username;
    private String password;

    private Integer id;
    @NotBlank(message="username is required.")
    @Size(min=4 , max=12, message="username must be between 4 and 12 characters long.")
    private String name;

    @NotBlank(message = "Email is required .")
    @Email(message = "Email must be valid")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
    private String email;


    @NotBlank(message = "Password is required .")
    @Size(min = 5 ,message = "password must be at least 5 characters")
    private String password;

    @NotNull(message = "RoleId is required.")
    private Integer roleId;


}
