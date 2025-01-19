package com.nuclear.realworld.api.model.Article;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.List;

public class ArticleWrapper {
    @JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
    private List<ArticleResponse> articles;
    private int articleCount;

    private ArticleWrapper(Builder builder) {
        setArticles(builder.articles);
        setArticleCount(builder.articleCount);
    }

    public List<ArticleResponse> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleResponse> articles) {
        this.articles = articles;
    }

    public int getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(int articleCount) {
        this.articleCount = articleCount;
    }


    public static final class Builder {
        private List<ArticleResponse> articles;
        private int articleCount;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder articles(List<ArticleResponse> val) {
            articles = val;
            return this;
        }

        public Builder articleCount(int val) {
            articleCount = val;
            return this;
        }

        public ArticleWrapper build() {
            return new ArticleWrapper(this);
        }
    }
}
