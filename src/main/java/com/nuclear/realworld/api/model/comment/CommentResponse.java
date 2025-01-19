package com.nuclear.realworld.api.model.comment;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.nuclear.realworld.api.model.BaseResponse;
import com.nuclear.realworld.api.model.profile.ProfileResponse;

import java.time.OffsetDateTime;

@JsonTypeName("comment")
public class CommentResponse extends BaseResponse {
    private Long id;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
    private String body;

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    private ProfileResponse author;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public ProfileResponse getAuthor() {
        return author;
    }

    public void setAuthor(ProfileResponse author) {
        this.author = author;
    }
}
