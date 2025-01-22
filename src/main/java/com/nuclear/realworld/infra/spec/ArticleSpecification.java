package com.nuclear.realworld.infra.spec;

import com.nuclear.realworld.domain.entity.Article;
import org.springframework.data.jpa.domain.Specification;

//@Join(path = "author", alias = "author")
//@Join(path = "tagList", alias = "tags")
//@Join(path = "favorites", alias = "fav")
//@And({@Spec(path = "author.username", params = "author",
//        spec = Like.class), @Spec(path = "tags.name", params = "tag",
//        spec = In.class), @Spec(path = "fav.username", params = "favorited",
//        spec = In.class)})
//public interface ArticleSpecification extends Specification<Article> {
//}


public class ArticleSpecification {

    public static Specification<Article> hasFavorited(String username) {
        return (root, query, criteriaBuilder) -> {
            if (username == null) {
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
            }
            return criteriaBuilder.equal(root.join("favorites").get("username"),
                                         username);
        };
    }

    public static Specification<Article> hasAuthor(String author) {
        return (root, query, criteriaBuilder) -> {
            if (author == null) {
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
            }
            return criteriaBuilder.equal(root.join("author").get("username"),
                                         author);
        };
    }

    public static Specification<Article> hasTag(String tag) {
        return (root, query, criteriaBuilder) -> {
            if (tag == null) {
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
            }
            return criteriaBuilder.equal(root.join("tagList").get("name"), tag);
        };
    }
}