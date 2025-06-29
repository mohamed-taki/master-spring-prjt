package com.supemir.expense_manager.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponse {
    // Getter
    private String token;

    public JwtResponse(String token) {
        this.token = token;
    }

}