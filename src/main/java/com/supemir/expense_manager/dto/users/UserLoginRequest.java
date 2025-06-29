package com.supemir.expense_manager.dto.users;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserLoginRequest {
    @NotNull(message = "Username is required")
    @NotBlank(message = "Username cannot be blank")
    private String username;
    @NotNull(message = "password is required")
    @NotBlank(message = "password cannot be blank")
    private String password;
}