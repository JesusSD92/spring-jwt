package com.springboot.backend.jesus.users_backend.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDto implements IUser {

    @NotBlank
    private String name;

    @NotBlank
    private String lastName;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 4, max = 12)
    private String username;

    private boolean admin;
}
