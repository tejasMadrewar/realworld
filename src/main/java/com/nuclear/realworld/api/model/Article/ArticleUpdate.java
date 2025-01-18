package com.nuclear.realworld.api.model.Article;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.nuclear.realworld.api.model.BaseResponse;
import jakarta.annotation.Nullable;

@JsonTypeName("article")
public class ArticleUpdate extends BaseResponse {

    @Nullable
    private String title;

    @Nullable
    private String description;

    @Nullable
    private String body;

    @Nullable
    public String getTitle() {
        return title;
    }

    public void setTitle(@Nullable String title) {
        this.title = title;
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    public void setDescription(@Nullable String description) {
        this.description = description;
    }

    @Nullable
    public String getBody() {
        return body;
    }

    public void setBody(@Nullable String body) {
        this.body = body;
    }
}
