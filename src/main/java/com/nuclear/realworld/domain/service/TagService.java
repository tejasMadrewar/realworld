package com.nuclear.realworld.domain.service;

import com.nuclear.realworld.domain.entity.Tag;
import com.nuclear.realworld.domain.repository.TagRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TagService {
    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public Tag save(Tag tag) {
        Optional<Tag> existingTag = tagRepository.findByName(tag.getName());
        return existingTag.map(tagRepository::save)
                .orElseGet(() -> tagRepository.save(tag));
    }

    public List<Tag> saveAll(List<String> tags) {
        return tags.stream().map(t -> {
            Tag tag = toEntity(t);
            return save(tag);
        }).toList();
    }

    private Tag toEntity(String name) {
        return Tag.Builder.builder().name(name).build();
    }

    @Transactional(readOnly = true)
    public List<Tag> listAll() {
        return tagRepository.findAll();
    }
}
