package com.nuclear.realworld.api.assembler;

import com.nuclear.realworld.api.model.profile.ProfileResponse;
import com.nuclear.realworld.domain.entity.Profile;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProfileAssembler {
    private final ModelMapper modelMapper = new ModelMapper();

    public ProfileResponse toResponse(Profile profile) {
        return modelMapper.map(profile, ProfileResponse.class);
    }

    public ProfileResponse toResponse(Profile current, Profile profile) {
        ProfileResponse response = toResponse(profile);

        boolean isFollowing = current.getProfiles().contains(profile);
        response.setFollowing(isFollowing);

        return response;
    }
}
