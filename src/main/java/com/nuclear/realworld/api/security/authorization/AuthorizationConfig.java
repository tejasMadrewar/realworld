package com.nuclear.realworld.api.security.authorization;

import com.nuclear.realworld.api.security.AuthUtils;
import com.nuclear.realworld.domain.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class AuthorizationConfig {

    private final AuthUtils authUtils;
    private final UserService userService;

    public AuthorizationConfig(AuthUtils authUtils, UserService userService) {
        this.authUtils = authUtils;
        this.userService = userService;
    }

    public boolean isAuthenticated() {
        return authUtils.isAuthenticated();
    }

}
