package com.supemir.expense_manager.service;

import com.supemir.expense_manager.entity.UserEntity;
import com.supemir.expense_manager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserEntity addUser(String username, String password, String email) {
        userRepository.findByUsername(username)
                .ifPresent(user -> {
                    throw new IllegalArgumentException("User already exists: " + username);
                });
        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setEmail(email);

        return userRepository.save(user);
    }
}
