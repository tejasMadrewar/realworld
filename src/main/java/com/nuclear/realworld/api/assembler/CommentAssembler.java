package com.nuclear.realworld.api.assembler;

import com.nuclear.realworld.api.model.comment.CommentRegister;
import com.nuclear.realworld.api.model.comment.CommentResponse;
import com.nuclear.realworld.api.model.comment.CommentWrapper;
import com.nuclear.realworld.domain.entity.Comment;
import com.nuclear.realworld.domain.entity.Profile;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommentAssembler {

    private final ModelMapper modelMapper = new ModelMapper();

    public CommentResponse toResponse(Comment comment) {
        return modelMapper.map(comment, CommentResponse.class);
    }

    public CommentResponse toResponse(Profile profile, Comment comment) {
        CommentResponse response = toResponse(comment);

        if (profile.getProfiles().contains(comment.getAuthor())) {
            response.getAuthor().setFollowing(true);
        }

        return response;
    }

    public CommentWrapper toCollectionResponse(List<Comment> comments) {
        List<CommentResponse> content = comments.stream().map(this::toResponse)
                .toList();

        return buildResponse(content);
    }

    public CommentWrapper toCollectionResponse(Profile profile,
                                               List<Comment> comments) {
        List<CommentResponse> content = comments.stream()
                .map(c -> toResponse(profile, c)).toList();
        return buildResponse(content);
    }

    private CommentWrapper buildResponse(List<CommentResponse> content) {
        return CommentWrapper.Builder.builder().comments(content).build();
    }

    public Comment toEntity(CommentRegister register) {
        return modelMapper.map(register, Comment.class);
    }
}
