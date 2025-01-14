package com.nuclear.realworld.api.model.user;

public class UserResponse {
    public String email;
    public String token;
    public String username;
    public String bio;
    public String image;

    private UserResponse(Builder builder) {
        setEmail(builder.email);
        setToken(builder.token);
        setUsername(builder.username);
        setBio(builder.bio);
        setImage(builder.image);
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public static final class Builder {
        private String email;
        private String token;
        private String username;
        private String bio;
        private String image;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder email(String val) {
            email = val;
            return this;
        }

        public Builder token(String val) {
            token = val;
            return this;
        }

        public Builder username(String val) {
            username = val;
            return this;
        }

        public Builder bio(String val) {
            bio = val;
            return this;
        }

        public Builder image(String val) {
            image = val;
            return this;
        }

        public UserResponse build() {
            return new UserResponse(this);
        }
    }
}
