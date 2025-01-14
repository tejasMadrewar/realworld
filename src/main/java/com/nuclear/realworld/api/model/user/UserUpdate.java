package com.nuclear.realworld.api.model.user;


import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;

public class UserUpdate {
    @Nullable
    public String username;
    @Email
    @Nullable
    private String email;
    @Nullable
    private String bio;
    @Nullable
    private String image;

    @Nullable
    private String password;
}
