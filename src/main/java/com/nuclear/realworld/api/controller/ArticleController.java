package com.nuclear.realworld.api.controller;

import com.nuclear.realworld.api.assembler.ArticleAssembler;
import com.nuclear.realworld.api.model.Article.ArticleRegister;
import com.nuclear.realworld.api.model.Article.ArticleResponse;
import com.nuclear.realworld.api.model.Article.ArticleUpdate;
import com.nuclear.realworld.api.model.Article.ArticleWrapper;
import com.nuclear.realworld.api.security.AuthUtils;
import com.nuclear.realworld.api.security.authorization.CheckSecurity;
import com.nuclear.realworld.domain.entity.Article;
import com.nuclear.realworld.domain.entity.Profile;
import com.nuclear.realworld.domain.entity.Tag;
import com.nuclear.realworld.domain.service.ArticleService;
import com.nuclear.realworld.domain.service.ProfileService;
import com.nuclear.realworld.domain.service.TagService;
import com.nuclear.realworld.domain.service.UserService;
import com.nuclear.realworld.infra.spec.ArticleSpecification;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/articles")
public class ArticleController {

    private static final String DEFAULT_FILTER_LIMIT = "20";
    private static final String DEFAULT_FILTER_OFFSET = "0";
    private static final Sort DEFAULT_FILTER_SORT = Sort.by(Sort.Direction.DESC,
                                                            "createdAt");

    final private ArticleAssembler articleAssembler;
    final private UserService userService;
    final private TagService tagService;
    final private ArticleService articleService;
    final private ProfileService profileService;
    final private AuthUtils authUtils;

    public ArticleController(ArticleAssembler articleAssembler,
                             UserService userService, TagService tagService,
                             ArticleService articleService,
                             ProfileService profileService,
                             AuthUtils authUtils) {
        this.articleAssembler = articleAssembler;
        this.userService = userService;
        this.tagService = tagService;
        this.articleService = articleService;
        this.profileService = profileService;
        this.authUtils = authUtils;
    }

    @GetMapping
    @CheckSecurity.Public.canRead
    public ArticleWrapper getAll(@RequestParam(required = false) String tag,//
                                 @RequestParam(required = false,
                                         defaultValue = DEFAULT_FILTER_OFFSET)
                                 int offset,//
                                 @RequestParam(required = false,
                                         defaultValue = DEFAULT_FILTER_LIMIT)
                                 int limit,//
                                 @RequestParam(required = false) String author,
//
                                 @RequestParam(required = false)
                                 String favorited) {

        Specification<Article> spec = Specification//
                .where(ArticleSpecification.hasTag(tag))//
                .and(ArticleSpecification.hasAuthor(author))//
                .and(ArticleSpecification.hasFavorited(favorited));

        Pageable pageable = PageRequest.of(offset, limit, DEFAULT_FILTER_SORT);

        List<Article> articles = articleService.listAll(spec, pageable)
                .getContent();

        if (authUtils.isAuthenticated()) {
            Profile profile = userService.getCurrentUser().getProfile();
            return articleAssembler.toCollectionModel(profile, articles);
        }
        return articleAssembler.toCollectionModel(articles);
    }


    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ArticleResponse createArticle(
            @RequestBody ArticleRegister register) {

        Profile profile = userService.getCurrentUser().getProfile();
        List<Tag> tags = new ArrayList<>();
        if (register.getTagList() != null) {
            tags = tagService.saveAll(register.getTagList().stream().toList());
        }

        Article article = articleAssembler.toEntity(register);

        return articleAssembler.toResponse(profile,
                                           articleService.save(article,
                                                               profile,
                                                               tags));

    }

    @GetMapping("{slug}")
    @CheckSecurity.Public.canRead
    public ArticleResponse getBySlug(@PathVariable String slug) {
        Article article = articleService.getBySlug(slug);

        if (authUtils.isAuthenticated()) {
            Profile profile = userService.getCurrentUser().getProfile();
            return articleAssembler.toResponse(profile, article);
        }
        return articleAssembler.toResponse(article);
    }

    @DeleteMapping("{slug}")
    @CheckSecurity.Articles.canManage
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteArticle(@PathVariable String slug) {
        Article article = articleService.getBySlug(slug);
        articleService.delete(article);
    }

    @PutMapping("{slug}")
    @CheckSecurity.Articles.canManage
    public ArticleResponse updateArticle(@PathVariable String slug,
                                         @RequestBody ArticleUpdate update) {
        Article article = articleService.getBySlug(slug);
        articleAssembler.copyToEntity(update, article);
        return articleAssembler.toResponse(articleService.save(article));
    }

    @GetMapping("feed")
    public String ArticleFeeds() {
        return "asdf";
    }


    @PostMapping("{slug}/favorite")
    @CheckSecurity.Protected.canManage
    public ArticleResponse favoriteArticle(@PathVariable String slug) {
        Article article = articleService.getBySlug(slug);
        Profile profile = userService.getCurrentUser().getProfile();

        profile = profileService.favorite(profile, article);
        article = articleService.profileFavorited(profile, article);
        return articleAssembler.toResponse(profile, article);
    }

    @DeleteMapping("{slug}/favorite")
    @CheckSecurity.Protected.canManage
    public ArticleResponse unfavoriteArticle(@PathVariable String slug) {
        Article article = articleService.getBySlug(slug);
        Profile profile = userService.getCurrentUser().getProfile();

        profile = profileService.unfavorite(profile, article);
        article = articleService.profileUnfavorited(profile, article);
        return articleAssembler.toResponse(profile, article);
    }

}
