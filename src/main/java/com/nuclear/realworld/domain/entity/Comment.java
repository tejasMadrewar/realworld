package com.nuclear.realworld.domain.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;
import java.util.Objects;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    private OffsetDateTime updatedAt;

    private String body;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Profile author;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    public Comment(Long id, OffsetDateTime createdAt, OffsetDateTime updatedAt,
                   String body, Profile author, Article article) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.body = body;
        this.author = author;
        this.article = article;
    }

    public Comment() {
    }

    private Comment(Builder builder) {
        setId(builder.id);
        setCreatedAt(builder.createdAt);
        setUpdatedAt(builder.updatedAt);
        setBody(builder.body);
        setAuthor(builder.author);
        setArticle(builder.article);
    }

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

    public Profile getAuthor() {
        return author;
    }

    public void setAuthor(Profile author) {
        this.author = author;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public static final class Builder {
        private Long id;
        private OffsetDateTime createdAt;
        private OffsetDateTime updatedAt;
        private String body;
        private Profile author;
        private Article article;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder id(Long val) {
            id = val;
            return this;
        }

        public Builder createdAt(OffsetDateTime val) {
            createdAt = val;
            return this;
        }

        public Builder updatedAt(OffsetDateTime val) {
            updatedAt = val;
            return this;
        }

        public Builder body(String val) {
            body = val;
            return this;
        }

        public Builder author(Profile val) {
            author = val;
            return this;
        }

        public Builder article(Article val) {
            article = val;
            return this;
        }

        public Comment build() {
            return new Comment(this);
        }
    }
}

