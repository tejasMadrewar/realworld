package com.nuclear.realworld.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "profiles")
public class Profile {

    String username;
    String bio;
    String image;

    @Id
    @Column(name = "user_id")
    @EqualsAndHashCode.Include
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(name = "profiles_following", joinColumns = @JoinColumn(name = "profile_id"), inverseJoinColumns = @JoinColumn(name = "following_id"))
    private Set<Profile> profiles = new HashSet<>();


    @Override
    public String toString() {
        return "Profile{" + "id=" + id + ", image='" + image + '\'' + ", bio='" + bio + '\'' + ", username='" + username + '\'' + '}';
    }

    public void followProfile(Profile profile) {
        getProfiles().add(profile);
    }

    public void unfollowProfile(Profile profile) {
        getProfiles().remove(profile);
    }

}