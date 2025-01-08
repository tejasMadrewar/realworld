package com.nuclear.realworld.api.model.user;

import lombok.Builder;

@Builder
public class UserResponse {
    public String email;
    public String token;
    public String username;
    public String bio;
    public String image;
}
