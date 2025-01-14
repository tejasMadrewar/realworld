package com.nuclear.realworld.domain.service;

import com.nuclear.realworld.domain.entity.Profile;
import com.nuclear.realworld.domain.entity.User;
import com.nuclear.realworld.domain.exception.ProfileNotFoundException;
import com.nuclear.realworld.domain.repository.ProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProfileService {
    final static String DEFAULT_IMG_URL = "http://test.com/test.jpg";
    final private ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Transactional(readOnly = true)
    public Profile getByUsername(String username) {
        return profileRepository.findByUsername(username)
                .orElseThrow(ProfileNotFoundException::new);
    }

    public Profile createNewProfile(User user, String username) {
        return Profile.Builder.builder().user(user).username(username).bio(null)
                .image(DEFAULT_IMG_URL).build();
    }

    @Transactional
    public Profile save(Profile profile) {
        return profileRepository.save(profile);
    }

    public void follow(Profile current, Profile toFollow) {
        current.followProfile(toFollow);
        profileRepository.save(current);
    }

    public void unfollow(Profile current, Profile toFollow) {
        current.unfollowProfile(toFollow);
        profileRepository.save(current);
    }

}
