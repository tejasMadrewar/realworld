package com.nuclear.realworld.domain.service;

import com.nuclear.realworld.domain.entity.Profile;
import com.nuclear.realworld.domain.entity.User;
import com.nuclear.realworld.domain.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {
    final static String DEFAULT_IMG_URL = "http://test.com/test.jpg";
    final private ProfileRepository profileRepository;

    public Profile createNewProfile(User user, String username) {
        return Profile.builder()
                .user(user)
                .username(username)
                .bio(null)
                .image(DEFAULT_IMG_URL)
                .build();
    }
}
