package com.supemir.expense_manager.controller;

import com.supemir.expense_manager.dto.JwtResponse;
import com.supemir.expense_manager.dto.users.UserLoginRequest;
import com.supemir.expense_manager.dto.users.UserRegisterRequest;
import com.supemir.expense_manager.dto.users.UserResponse;
import com.supemir.expense_manager.exception.UserNotFoundException;
import com.supemir.expense_manager.entity.UserEntity;
import com.supemir.expense_manager.mapper.UserMapper;
import com.supemir.expense_manager.service.JwtService;
import com.supemir.expense_manager.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.AuthenticationException;

@RestController()
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @PostMapping("login")
    public ResponseEntity<?> login(@Valid @RequestBody UserLoginRequest loginRequest) throws UserNotFoundException {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );
            String jwt = jwtService.generateToken(authentication);
            return ResponseEntity.ok(new JwtResponse(jwt));
        }
        catch (AuthenticationException e) {
            throw new UserNotFoundException(loginRequest.getUsername());
        }
    }

    @PostMapping("register")
    public ResponseEntity<?> register(@Valid @RequestBody UserRegisterRequest registerRequest) {
        UserEntity user = userService.addUser(
                registerRequest.getUsername(),
                registerRequest.getPassword(),
                registerRequest.getEmail()
        );
        String token = jwtService.generateToken(registerRequest.getUsername());
        UserResponse userResponse = userMapper.toResponse(user);
        userResponse.setToken(token);

        return ResponseEntity.ok(userResponse);
    }
}
