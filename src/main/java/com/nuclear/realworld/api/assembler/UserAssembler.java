package com.nuclear.realworld.api.assembler;

import com.nuclear.realworld.api.model.UserRegister;
import com.nuclear.realworld.api.model.UserResponse;
import com.nuclear.realworld.domain.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserAssembler {

    private final ModelMapper modelMapper = new ModelMapper();

    public User toEntity(UserRegister register) {
        return modelMapper.map(register, User.class);
    }

    public UserResponse toResponse(User user) {
        return UserResponse.builder()
                .username(user.getProfile()
                        .getUsername())
                .email(user.getEmail())
                .token(user.getToken())
                .bio(user.getProfile()
                        .getBio())
                .image(user.getProfile()
                        .getImage())
                .build();
    }
}