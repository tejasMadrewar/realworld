package com.nuclear.realworld.api.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Error {
    private String status;
    private String message;
    private Map<String, Object> errors;

    private Error(Builder builder) {
        setStatus(builder.status);
        setMessage(builder.message);
        setErrors(builder.errors);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, Object> errors) {
        this.errors = errors;
    }

    public static final class Builder {
        private String status;
        private String message;
        private Map<String, Object> errors;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder status(String val) {
            status = val;
            return this;
        }

        public Builder message(String val) {
            message = val;
            return this;
        }

        public Builder errors(Map<String, Object> val) {
            errors = val;
            return this;
        }

        public Error build() {
            return new Error(this);
        }
    }
}
