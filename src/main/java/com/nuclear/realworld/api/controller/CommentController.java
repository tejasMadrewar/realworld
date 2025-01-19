package com.nuclear.realworld.api.controller;

import com.nuclear.realworld.api.assembler.CommentAssembler;
import com.nuclear.realworld.api.model.comment.CommentRegister;
import com.nuclear.realworld.api.model.comment.CommentResponse;
import com.nuclear.realworld.api.model.comment.CommentWrapper;
import com.nuclear.realworld.api.security.AuthUtils;
import com.nuclear.realworld.api.security.authorization.CheckSecurity;
import com.nuclear.realworld.domain.entity.Article;
import com.nuclear.realworld.domain.entity.Comment;
import com.nuclear.realworld.domain.entity.Profile;
import com.nuclear.realworld.domain.service.ArticleService;
import com.nuclear.realworld.domain.service.CommentService;
import com.nuclear.realworld.domain.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/articles/{slug}/comments")
public class CommentController {

    private final ArticleService articleService;
    private final AuthUtils authUtils;
    private final UserService userService;
    private final CommentAssembler commentAssembler;
    private final CommentService commentService;

    public CommentController(ArticleService articleService, AuthUtils authUtils,
                             UserService userService,
                             CommentAssembler commentAssembler,
                             CommentService commentService) {
        this.articleService = articleService;
        this.authUtils = authUtils;
        this.userService = userService;
        this.commentAssembler = commentAssembler;
        this.commentService = commentService;
    }

    @CheckSecurity.Public.canRead
    @GetMapping
    public CommentWrapper listByArticle(@PathVariable String slug) {
        Article article = articleService.getBySlug(slug);

        if (authUtils.isAuthenticated()) {
            Profile profile = userService.getCurrentUser().getProfile();

        }

        return commentAssembler.toCollectionResponse(commentService.getAllByArticle(
                article));
    }

    @PostMapping
    @CheckSecurity.Protected.canManage
    @ResponseStatus(HttpStatus.CREATED)
    public CommentResponse save(@PathVariable String slug,
                                @Valid @RequestBody CommentRegister register) {

        Comment comment = commentAssembler.toEntity(register);
        Article article = articleService.getBySlug(slug);
        Profile author = userService.getCurrentUser().getProfile();

        return commentAssembler.toResponse(commentService.save(comment,
                                                               article,
                                                               author));
    }

    @DeleteMapping("/{commentId}")
    @CheckSecurity.Comments.canDelete
    public void delete(@PathVariable String slug,
                       @PathVariable Long commentId) {
        Article article = articleService.getBySlug(slug);
        Comment comment = commentService.getById(commentId);
        commentService.delete(comment);
    }


}
