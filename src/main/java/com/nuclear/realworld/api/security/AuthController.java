package com.nuclear.realworld.api.security;

import com.nuclear.realworld.api.assembler.UserAssembler;
import com.nuclear.realworld.api.model.UserAuthenticate;
import com.nuclear.realworld.api.model.UserRegister;
import com.nuclear.realworld.api.model.UserResponse;
import com.nuclear.realworld.domain.entity.Profile;
import com.nuclear.realworld.domain.entity.User;
import com.nuclear.realworld.domain.service.ProfileService;
import com.nuclear.realworld.domain.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("users")
public class AuthController {
    final private AuthService authService;
    final private ProfileService profileService;
    final private UserService userService;
    final private UserAssembler userAssembler;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse registerUser(@Valid @RequestBody UserRegister reg) {

        User user = userAssembler.toEntity(reg);
        Profile profile = profileService.createNewProfile(user,
                reg.getUsername());

        return authService.registerUser(userService.save(user, profile));
    }

    @PostMapping("login")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse loginUser(@Valid @RequestBody UserAuthenticate login) {
        return authService.authenticate(login);
    }

}
