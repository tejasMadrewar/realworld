package com.nuclear.realworld.domain.repository;

import com.nuclear.realworld.domain.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long>,
        JpaSpecificationExecutor<Article> {
    Optional<Article> findBySlug(String slug);
}
