package com.nuclear.realworld.domain.service;

import com.github.slugify.Slugify;
import com.nuclear.realworld.api.model.Article.ArticleSpecification;
import com.nuclear.realworld.domain.entity.Article;
import com.nuclear.realworld.domain.entity.Profile;
import com.nuclear.realworld.domain.entity.Tag;
import com.nuclear.realworld.domain.exception.ArticleNotFoundException;
import com.nuclear.realworld.domain.exception.ArticleNotUniqueException;
import com.nuclear.realworld.domain.repository.ArticleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final Slugify slg;

    public ArticleService(ArticleRepository articleRepository, Slugify slg) {
        this.articleRepository = articleRepository;
        this.slg = slg;
    }

    @Transactional(readOnly = true)
    public Page<Article> listAll(ArticleSpecification filter,
                                 Pageable pageable) {
        //return articleRepository.findAll(filter, pageable);
        return articleRepository.findAll(pageable); // todo: implement the
        // specification with user param
    }

    @Transactional
    public Article save(Article article, Profile profile, List<Tag> tags) {
        addAllTags(article, tags);
        article.setAuthor(profile);
        return save(article);
    }

    @Transactional
    public Article save(Article article) {
        var slug = slg.slugify(article.getTitle());
        checkSlugAvailability(slug, article);
        article.setSlug(slug);
        return articleRepository.save(article);
    }

    private void addAllTags(Article article, List<Tag> tags) {
        article.setTagList(new HashSet<>());
        tags.forEach(article::addTag);
    }

    private void checkSlugAvailability(String slug, Article article) {
        if (slugTaken(slug, article)) throw new ArticleNotUniqueException();
    }

    private boolean slugTaken(String slug, Article article) {
        Optional<Article> existingArticle = articleRepository.findBySlug(slug);
        return existingArticle.isPresent() && !existingArticle.get()
                .equals(article);
    }

    @Transactional(readOnly = true)
    public Article getBySlug(String slug) {
        return articleRepository.findBySlug(slug)
                .orElseThrow(ArticleNotFoundException::new);
    }

    @Transactional
    public Article profileFavorited(Profile profile, Article article) {
        article.addFavorite(profile);
        return articleRepository.save(article);
    }

    @Transactional
    public Article profileUnfavorited(Profile profile, Article article) {
        article.removeFavorite(profile);
        return articleRepository.save(article);
    }

    @Transactional
    public void delete(Article article) {
        Set<Profile> favorites = article.getFavorites();
        favorites.forEach(u -> u.unfavoriteArticle(article));
        articleRepository.delete(article);
    }

}
