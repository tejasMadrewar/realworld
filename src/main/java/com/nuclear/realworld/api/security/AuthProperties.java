package com.nuclear.realworld.api.security;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@Setter
@Getter
@Validated
@ConfigurationProperties("api.security")
public class AuthProperties {

    @NotNull
    private Token token;

    @Getter
    @Setter
    @Validated
    public static class Token {
        @NotBlank
        private String secret;

        @NotNull
        private Long expiration;
    }
}
