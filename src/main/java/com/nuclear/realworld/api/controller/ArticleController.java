package com.nuclear.realworld.api.controller;

import com.nuclear.realworld.api.assembler.ArticleAssembler;
import com.nuclear.realworld.api.model.Article.ArticleRegister;
import com.nuclear.realworld.api.model.Article.ArticleResponse;
import com.nuclear.realworld.domain.entity.Article;
import com.nuclear.realworld.domain.entity.Profile;
import com.nuclear.realworld.domain.entity.Tag;
import com.nuclear.realworld.domain.service.ArticleService;
import com.nuclear.realworld.domain.service.TagService;
import com.nuclear.realworld.domain.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/articles")
public class ArticleController {

    final private ArticleAssembler articleAssembler;
    final private UserService userService;
    final private TagService tagService;
    final private ArticleService articleService;

    public ArticleController(ArticleAssembler articleAssembler,
                             UserService userService, TagService tagService,
                             ArticleService articleService) {
        this.articleAssembler = articleAssembler;
        this.userService = userService;
        this.tagService = tagService;
        this.articleService = articleService;
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
                articleService.save(article, profile, tags));

    }

    @PutMapping("")
    public String updateArticle() {
        return "asdf";
    }

    @DeleteMapping("")
    public String deleteArticle() {
        return "asdf";
    }

    @GetMapping("feed")
    public String ArticleFeeds() {
        return "asdf";
    }

    @GetMapping("feed/{slug}")
    public String getArticle(@PathVariable String slug) {
        return "asdf";
    }

}
