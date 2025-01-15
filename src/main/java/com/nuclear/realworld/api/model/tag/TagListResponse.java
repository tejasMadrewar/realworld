package com.nuclear.realworld.api.model.tag;


import java.util.List;

public class TagListResponse {
    private List<String> tags;

    private TagListResponse(Builder builder) {
        setTags(builder.tags);
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }


    public static final class Builder {
        private List<String> tags;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder tags(List<String> val) {
            tags = val;
            return this;
        }

        public TagListResponse build() {
            return new TagListResponse(this);
        }
    }
}
