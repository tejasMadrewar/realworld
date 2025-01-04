package com.nuclear.realworld.domain.service;

import com.nuclear.realworld.domain.entity.Profile;
import com.nuclear.realworld.domain.entity.User;
import com.nuclear.realworld.domain.exception.EmailNotAvilableException;
import com.nuclear.realworld.domain.exception.UsernameNotAvilableException;
import com.nuclear.realworld.domain.repository.ProfileRepository;
import com.nuclear.realworld.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    final private UserRepository userRepository;
    final private ProfileRepository profileRepository;
    final private PasswordEncoder passwordEncoder;

    public User createUser(User user, Profile profile) {

        if (user.getId() == null) {
            user.setProfile(profile);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            checkUsernameAvailability(profile.getUsername());
            checkEmailAvailablity(user.getEmail());
        }

        user = userRepository.save(user);

        return user;
    }

    private void checkEmailAvailablity(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new EmailNotAvilableException();
        }
    }

    private void checkUsernameAvailability(String username) {
        if (profileRepository.existsByUsername(username)) {
            throw new UsernameNotAvilableException();
        }
    }
}
