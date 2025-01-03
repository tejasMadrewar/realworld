package com.nuclear.realworld.domain.service;

import com.nuclear.realworld.api.assembler.UserAssembler;
import com.nuclear.realworld.api.model.UserResponse;
import com.nuclear.realworld.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {
    final private UserService userService;
    final private UserAssembler userAssembler;

    public UserResponse registerUser(User user) {
        return userAssembler.toResponse(userService.createUser(user));
    }
}
