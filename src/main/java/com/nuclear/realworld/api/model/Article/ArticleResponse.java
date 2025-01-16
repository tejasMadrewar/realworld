package com.nuclear.realworld.api.model.Article;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.nuclear.realworld.api.model.BaseResponse;
import com.nuclear.realworld.api.model.profile.ProfileResponse;

import java.time.OffsetDateTime;
import java.util.List;

@JsonTypeName("article")
public class ArticleResponse extends BaseResponse {

    private String slug;
    private String title;
    private String description;
    private String body;
    private List<String> tagList;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
    private Boolean favorited;
    private Integer favoritesCount;

    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
    private ProfileResponse author;

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<String> getTagList() {
        return tagList;
    }

    public void setTagList(List<String> tagList) {
        this.tagList = tagList;
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

    public Boolean getFavorited() {
        return favorited;
    }

    public void setFavorited(Boolean favorited) {
        this.favorited = favorited;
    }

    public Integer getFavoritesCount() {
        return favoritesCount;
    }

    public void setFavoritesCount(Integer favoritesCount) {
        this.favoritesCount = favoritesCount;
    }

    public ProfileResponse getAuthor() {
        return author;
    }

    public void setAuthor(ProfileResponse author) {
        this.author = author;
    }
}
