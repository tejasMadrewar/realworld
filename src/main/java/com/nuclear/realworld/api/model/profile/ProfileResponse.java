package com.nuclear.realworld.api.model.profile;

import lombok.Data;

@Data
public class ProfileResponse {
    String username;
    String bio;
    String image;
    Boolean following;
}
