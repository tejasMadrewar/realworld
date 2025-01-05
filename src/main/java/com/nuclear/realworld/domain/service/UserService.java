package com.nuclear.realworld.domain.service;

import com.nuclear.realworld.api.security.exception.EmailNotFoundException;
import com.nuclear.realworld.domain.entity.Profile;
import com.nuclear.realworld.domain.entity.User;
import com.nuclear.realworld.domain.exception.EmailNotAvailableException;
import com.nuclear.realworld.domain.exception.UsernameNotAvilableException;
import com.nuclear.realworld.domain.repository.ProfileRepository;
import com.nuclear.realworld.domain.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    final private UserRepository userRepository;
    final private ProfileRepository profileRepository;
    final private ProfileService profileService;
    final private PasswordEncoder passwordEncoder;
    final private EntityManager entityManager;

    @Transactional
    public User save(User user, Profile profile) {

        if (user.getId() == null) {
            checkUserAvailable(user, profile);
            user.setProfile(profile);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        user = userRepository.save(user);
        profileService.save(profile);
        return user;
    }

    private void checkUserAvailable(User user, Profile profile) {
        checkUsernameAvailability(profile.getUsername());
        checkEmailAvailablity(user.getEmail());
    }

    private void checkEmailAvailablity(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new EmailNotAvailableException();
        }
    }

    private void checkUsernameAvailability(String username) {
        if (profileRepository.existsByUsername(username)) {
            throw new UsernameNotAvilableException();
        }
    }

    @Transactional
    public void setToken(User user, String token) {
        user.setToken(token);
        userRepository.save(user);
    }

    public User getByEmail(@NotBlank String email) {
        return userRepository.getByEmail(email)
                .orElseThrow(EmailNotFoundException::new);
    }
}
