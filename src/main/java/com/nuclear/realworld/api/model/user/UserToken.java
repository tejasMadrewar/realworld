package com.nuclear.realworld.api.model.user;


public class UserToken {
    Long id;

    private UserToken(Builder builder) {
        setId(builder.id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static final class Builder {
        private Long id;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder id(Long val) {
            id = val;
            return this;
        }

        public UserToken build() {
            return new UserToken(this);
        }
    }
}
