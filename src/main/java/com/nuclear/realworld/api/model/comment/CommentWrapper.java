package com.nuclear.realworld.api.model.comment;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.List;

public class CommentWrapper {

    @JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
    private List<CommentResponse> comments;

    private CommentWrapper(Builder builder) {
        setComments(builder.comments);
    }

    public List<CommentResponse> getComments() {
        return comments;
    }

    public void setComments(List<CommentResponse> comments) {
        this.comments = comments;
    }

    public static final class Builder {
        private List<CommentResponse> comments;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder comments(List<CommentResponse> val) {
            comments = val;
            return this;
        }

        public CommentWrapper build() {
            return new CommentWrapper(this);
        }
    }
}
