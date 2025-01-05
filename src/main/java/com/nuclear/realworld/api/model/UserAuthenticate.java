package com.nuclear.realworld.api.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Builder
@Data
public class UserAuthenticate {
    @NotBlank
    public String email;
    @NotBlank
    public String password;
}
