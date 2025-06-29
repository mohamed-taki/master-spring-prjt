package com.supemir.expense_manager.dto.users;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterRequest {
    @NotBlank(message = "Username should not be empty")
    @NotNull(message = "Username is required")
    private String username;

    @NotBlank(message = "Password Should not be empty")
    @NotNull(message = "Password is required")
    private String password;

    @Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Email should be valid")
    private String email;
}
