package com.nuclear.realworld.api.controller;

import com.nuclear.realworld.api.assembler.UserAssembler;
import com.nuclear.realworld.api.model.user.UserResponse;
import com.nuclear.realworld.api.model.user.UserUpdate;
import com.nuclear.realworld.api.security.authorization.CheckSecurity;
import com.nuclear.realworld.domain.entity.User;
import com.nuclear.realworld.domain.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    final private UserService userService;
    final private UserAssembler userAssembler;

    public UserController(UserService userService,
                          UserAssembler userAssembler) {
        this.userService = userService;
        this.userAssembler = userAssembler;
    }

    @GetMapping("")
    @CheckSecurity.Protected.canManage
    UserResponse getCurrentUser(Authentication authentication) {
        User user = userService.getByEmail(authentication.getName());
        return userAssembler.toResponse(user);
    }

    @PutMapping("")
    @CheckSecurity.Protected.canManage
    UserResponse updateCurrentUser(@RequestBody UserUpdate userUpdate) {
        User currentUser = userService.getCurrentUser();
        userAssembler.copyToEntity(userUpdate, currentUser);
        return userAssembler.toResponse(userService.save(currentUser,
                currentUser.getProfile()));
    }
}
