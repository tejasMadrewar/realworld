package com.nuclear.realworld.api.assembler;

import com.nuclear.realworld.api.model.Article.ArticleRegister;
import com.nuclear.realworld.api.model.Article.ArticleResponse;
import com.nuclear.realworld.domain.entity.Article;
import com.nuclear.realworld.domain.entity.Profile;
import com.nuclear.realworld.domain.entity.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ArticleAssembler {

    ModelMapper modelMapper = new ModelMapper();

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
}
