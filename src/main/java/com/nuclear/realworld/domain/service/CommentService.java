package com.nuclear.realworld.domain.service;

import com.nuclear.realworld.domain.entity.Article;
import com.nuclear.realworld.domain.entity.Comment;
import com.nuclear.realworld.domain.entity.Profile;
import com.nuclear.realworld.domain.exception.CommentNotFoundException;
import com.nuclear.realworld.domain.repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> getAllByArticle(Article article) {
        return commentRepository.findAllByArticle(article);
    }

    @Transactional
    public Comment getById(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(CommentNotFoundException::new);
    }

    @Transactional
    public Comment save(Comment comment, Article article, Profile author) {
        comment.setAuthor(author);
        comment.setArticle(article);
        return commentRepository.save(comment);
    }

    @Transactional
    public void delete(Comment comment) {
        commentRepository.delete(comment);
    }
}
