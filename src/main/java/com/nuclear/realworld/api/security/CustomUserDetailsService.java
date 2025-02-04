package com.nuclear.realworld.api.security;

import com.nuclear.realworld.domain.entity.User;
import com.nuclear.realworld.domain.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    final private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(
            String username) throws UsernameNotFoundException {
        User user = userRepository.getByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "User with username '" + username + ", does not found" + "."));
        return new CustomUserDetails(user);
    }
}
