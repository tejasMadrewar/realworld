package com.nuclear.realworld.api.model.user;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.nuclear.realworld.api.model.BaseResponse;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@JsonTypeName("user")
public class UserRegister extends BaseResponse {
    @NotBlank
    public String username;

    @NotBlank
    @Email
    public String email;

    @NotBlank
    public String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

