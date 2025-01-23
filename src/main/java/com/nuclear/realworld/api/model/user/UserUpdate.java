package com.nuclear.realworld.api.model.user;


import com.fasterxml.jackson.annotation.JsonTypeName;
import com.nuclear.realworld.api.model.BaseResponse;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;

@JsonTypeName("user")
public class UserUpdate extends BaseResponse {
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
