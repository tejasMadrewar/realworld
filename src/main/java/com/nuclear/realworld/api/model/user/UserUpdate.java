package com.nuclear.realworld.api.model.user;


import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdate {
    @Email
    @Nullable
    private String email;

    @Nullable
    private String bio;

    @Nullable
    public String username;

    @Nullable
    private String image;

    @Nullable
    private String password;
}
