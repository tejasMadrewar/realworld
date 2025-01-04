package com.nuclear.realworld.domain.repository;

import com.nuclear.realworld.domain.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile,Long> {
    boolean existsByUsername(String username);
}
