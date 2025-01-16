package com.nuclear.realworld.api.security.authorization;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public @interface CheckSecurity {
    @interface Public {
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @PreAuthorize("permitAll()")
        @interface canRead {
        }
    }

    @interface Protected {
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @PreAuthorize("@authorizationConfig.isAuthenticated")
        @interface canManage {
        }
    }

    @interface Articles {
        @Retention(RetentionPolicy.RUNTIME)
        @Target(ElementType.METHOD)
        @PreAuthorize("@authorizationConfig.isArticleAuthor(#slug)")
        @interface canManage {
        }
    }

}
