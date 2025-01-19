package com.nuclear.realworld.api.controller;

import com.nuclear.realworld.api.assembler.TagAssembler;
import com.nuclear.realworld.api.model.tag.TagListResponse;
import com.nuclear.realworld.api.security.authorization.CheckSecurity;
import com.nuclear.realworld.domain.service.TagService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/tags")
public class TagController {
    private final TagService tagService;
    private final TagAssembler tagAssembler;

    public TagController(TagService tagService, TagAssembler tagAssembler) {
        this.tagService = tagService;
        this.tagAssembler = tagAssembler;
    }

    @GetMapping("")
    @CheckSecurity.Public.canRead
    public TagListResponse list() {
        return tagAssembler.toCollectionResponse(tagService.listAll());
    }

}
