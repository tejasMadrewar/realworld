package com.nuclear.realworld.api.security;

import com.nuclear.realworld.api.exception.RestAccessDeniedHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private static final String[] PUBLIC_WRITE_ENDPOINTS = {"/users", "users/login"};
    private static final String[] PUBLIC_READ_ENDPOINTS = {"/profiles"};

    private final JwtSecurityFilter securityFilter;
    private final RestAccessDeniedHandler restAccessDeniedHandler;

    public SecurityConfig(JwtSecurityFilter securityFilter,
                          RestAccessDeniedHandler restAccessDeniedHandler) {
        this.securityFilter = securityFilter;
        this.restAccessDeniedHandler = restAccessDeniedHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize.requestMatchers(
                                HttpMethod.POST,
                                PUBLIC_WRITE_ENDPOINTS).permitAll()
                        .requestMatchers(HttpMethod.GET, PUBLIC_READ_ENDPOINTS)
                        .permitAll().anyRequest().authenticated())
                .anonymous(AbstractHttpConfigurer::disable)
                .exceptionHandling(handler -> handler.accessDeniedHandler(
                        restAccessDeniedHandler))
                .addFilterBefore(securityFilter,
                                 UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

}
