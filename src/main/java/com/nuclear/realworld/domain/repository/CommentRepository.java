package com.nuclear.realworld.domain.repository;

import com.nuclear.realworld.domain.entity.Article;
import com.nuclear.realworld.domain.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByArticle(Article article);
}
