package com.supemir.expense_manager.dto.users;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
    private String username;
    private Long id;
    private String token;
}
