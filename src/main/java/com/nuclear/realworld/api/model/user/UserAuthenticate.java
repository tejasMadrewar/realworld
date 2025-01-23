package com.nuclear.realworld.api.model.user;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.nuclear.realworld.api.model.BaseResponse;
import jakarta.validation.constraints.NotBlank;

@JsonTypeName("user")
public class UserAuthenticate extends BaseResponse {
    @NotBlank
    public String email;
    @NotBlank
    public String password;

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
