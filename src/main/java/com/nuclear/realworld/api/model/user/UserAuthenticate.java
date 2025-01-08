package com.nuclear.realworld.api.model.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserAuthenticate {
    @NotBlank
    public String email;
    @NotBlank
    public String password;
}
