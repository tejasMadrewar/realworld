package com.nuclear.realworld.api.controller;

import com.nuclear.realworld.api.assembler.UserAssembler;
import com.nuclear.realworld.api.model.UserRegister;
import com.nuclear.realworld.api.model.UserResponse;
import com.nuclear.realworld.domain.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("users")
public class AuthController {
    final private AuthService authService;
    final private UserAssembler userAssembler;

    @PostMapping("")
    public UserResponse registerUser(@RequestBody UserRegister reg) {
        return authService.registerUser(userAssembler.toEntity(reg));
    }
}
