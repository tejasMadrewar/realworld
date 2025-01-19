package com.nuclear.realworld.api.model.comment;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.nuclear.realworld.api.model.BaseResponse;
import jakarta.validation.constraints.NotBlank;

@JsonTypeName("comment")
public class CommentRegister extends BaseResponse {

    @NotBlank
    private String body;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
