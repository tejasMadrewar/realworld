package com.nuclear.realworld.api.security.authorization;

import com.nuclear.realworld.api.security.AuthUtils;
import com.nuclear.realworld.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthorizationConfig {

    private final AuthUtils authUtils;
    private final UserService userService;

    public boolean isAuthenticated() {
        return authUtils.isAuthenticated();
    }

}
