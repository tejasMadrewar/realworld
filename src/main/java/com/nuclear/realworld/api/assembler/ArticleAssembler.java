package com.nuclear.realworld.api.assembler;

import com.nuclear.realworld.api.model.Article.ArticleRegister;
import com.nuclear.realworld.api.model.Article.ArticleResponse;
import com.nuclear.realworld.api.model.Article.ArticleUpdate;
import com.nuclear.realworld.api.model.Article.ArticleWrapper;
import com.nuclear.realworld.domain.entity.Article;
import com.nuclear.realworld.domain.entity.Profile;
import com.nuclear.realworld.domain.entity.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ArticleAssembler {

    ModelMapper modelMapper = new ModelMapper();

    public void copyToEntity(ArticleUpdate update, Article article) {
        if (update.getTitle() != null) article.setTitle(update.getTitle());
        if (update.getDescription() != null)
            article.setDescription(update.getDescription());
        if (update.getBody() != null) article.setBody(update.getBody());
    }

    public ArticleResponse toResponse(Article article) {
        ArticleResponse response = modelMapper.map(article,
                                                   ArticleResponse.class);
        response.setTagList(tagsToList(article.getTagList().stream().toList()));
        return response;
    }

    public ArticleResponse toResponse(Profile profile, Article article) {
        ArticleResponse response = toResponse(article);

        Boolean isFollowingProfile = profile.getProfiles()
                .contains(article.getAuthor());
        response.getAuthor().setFollowing(isFollowingProfile);

        Boolean isArticleFavorite = article.getFavorites().contains(profile);
        response.setFavorited(isArticleFavorite);

        return response;
    }

    public Article toEntity(ArticleRegister register) {
        return modelMapper.map(register, Article.class);
    }

    private List<String> tagsToList(List<Tag> tags) {
        return tags.stream().map(Tag::getName).toList();
    }

    public ArticleWrapper toCollectionModel(List<Article> articles) {
        List<ArticleResponse> content = articles.stream().map(this::toResponse)
                .toList();
        return buildResponse(content);
    }

    public ArticleWrapper toCollectionModel(Profile profile,
                                            List<Article> articles) {
        List<ArticleResponse> content = articles.stream()
                .map(a -> toResponse(profile, a)).toList();
        return buildResponse(content);
    }

    private ArticleWrapper buildResponse(List<ArticleResponse> articles) {
        return ArticleWrapper.Builder.builder().articles(articles)
                .articleCount(articles.size()).build();
    }
}
