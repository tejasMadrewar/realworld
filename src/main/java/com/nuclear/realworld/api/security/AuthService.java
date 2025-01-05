package com.nuclear.realworld.api.security;

import com.nuclear.realworld.api.assembler.UserAssembler;
import com.nuclear.realworld.api.model.UserAuthenticate;
import com.nuclear.realworld.api.model.UserResponse;
import com.nuclear.realworld.api.model.UserToken;
import com.nuclear.realworld.domain.entity.Profile;
import com.nuclear.realworld.domain.entity.User;
import com.nuclear.realworld.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@RequiredArgsConstructor
@Service
public class AuthService {
    final private UserService userService;
    final private TokenService tokenService;
    final private UserAssembler userAssembler;
    final private AuthenticationManager authenticationManager;

    public UserResponse registerUser(User user) {
        String token = tokenService.generateToken(defaultClaims(user),
                user.getEmail());
        userService.setToken(user, token);
        return toResponse(user);
    }

    public UserResponse authenticate(UserAuthenticate authenticate) {
        Authentication auth = authenticationManager.authenticate((new UsernamePasswordAuthenticationToken(
                authenticate.getEmail(),
                authenticate.getPassword())));
        if (auth.isAuthenticated()) {

            User user = userService.getByEmail(auth.getName());
            String token = tokenService.generateToken(defaultClaims(user),
                    user.getEmail());
            userService.setToken(user, token);
            return toResponse(user);
        }

        throw new BadCredentialsException("Bad credentials.");
    }

    private UserResponse toResponse(User user) {
        return userAssembler.toResponse(user);
    }

    private HashMap<String, Object> defaultClaims(User user) {
        HashMap<String, Object> claims = new HashMap<>();
        UserToken userToken = UserToken.builder()
                .id(user.getId())
                .build();

        claims.put("user", userToken);
        return claims;
    }

}
