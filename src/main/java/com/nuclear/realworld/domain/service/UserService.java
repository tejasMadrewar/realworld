package com.nuclear.realworld.domain.service;

import com.nuclear.realworld.domain.entity.User;
import com.nuclear.realworld.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    final private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }
}
