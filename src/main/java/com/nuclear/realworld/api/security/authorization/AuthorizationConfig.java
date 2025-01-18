package com.nuclear.realworld.api.security.authorization;

import com.nuclear.realworld.api.security.AuthUtils;
import com.nuclear.realworld.domain.entity.Article;
import com.nuclear.realworld.domain.entity.Profile;
import com.nuclear.realworld.domain.service.ArticleService;
import com.nuclear.realworld.domain.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class AuthorizationConfig {

    private final AuthUtils authUtils;
    private final ArticleService articleService;
    private final UserService userService;

    public AuthorizationConfig(AuthUtils authUtils,
                               ArticleService articleService,
                               UserService userService) {
        this.authUtils = authUtils;
        this.articleService = articleService;
        this.userService = userService;
    }

    public boolean isArticleAuthor(String slug) {
        if (!isAuthenticated()) {
            return false;
        }

        Article article = articleService.getBySlug(slug);
        Profile author = article.getAuthor();

        return authenticatedUserEquals(author);
    }

    private boolean authenticatedUserEquals(Profile user) {
        return userService.getCurrentUser().getProfile().equals(user);
    }

    public boolean isAuthenticated() {
        return authUtils.isAuthenticated();
    }

}
