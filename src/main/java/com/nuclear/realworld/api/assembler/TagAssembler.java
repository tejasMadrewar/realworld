package com.nuclear.realworld.api.assembler;

import com.nuclear.realworld.api.model.tag.TagListResponse;
import com.nuclear.realworld.domain.entity.Tag;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TagAssembler {
    public TagListResponse toCollectionResponse(List<Tag> tags) {
        List<String> tagNames = tags.stream().map(Tag::getName).toList();
        return TagListResponse.Builder.builder().tags(tagNames).build();

    }
}
