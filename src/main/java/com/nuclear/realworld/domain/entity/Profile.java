package com.nuclear.realworld.domain.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "profiles")
public class Profile {

    String username;
    String bio;
    String image;

    @Id
    @Column(name = "user_id")
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(name = "profiles_following",
            joinColumns = @JoinColumn(name = "profile_id"),
            inverseJoinColumns = @JoinColumn(name = "following_id"))
    private Set<Profile> profiles = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "profiles_articles",
            joinColumns = @JoinColumn(name = "profile_id"),
            inverseJoinColumns = @JoinColumn(name = "article_id"))
    private Set<Article> articles = new HashSet<>();

    public Profile() {
    }

    public Profile(String username, String bio, String image, Long id,
                   User user, Set<Profile> profiles) {
        this.username = username;
        this.bio = bio;
        this.image = image;
        this.id = id;
        this.user = user;
        this.profiles = profiles;
    }

    private Profile(Builder builder) {
        setUsername(builder.username);
        setBio(builder.bio);
        setImage(builder.image);
        setId(builder.id);
        setUser(builder.user);
        setProfiles(builder.profiles);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Profile profile = (Profile) o;
        return Objects.equals(id, profile.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Profile> getProfiles() {
        return profiles;
    }

    public void setProfiles(Set<Profile> profiles) {
        this.profiles = profiles;
    }

    public static final class Builder {
        private String username;
        private String bio;
        private String image;
        private Long id;
        private User user;
        private Set<Profile> profiles;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder username(String val) {
            username = val;
            return this;
        }

        public Builder bio(String val) {
            bio = val;
            return this;
        }

        public Builder image(String val) {
            image = val;
            return this;
        }

        public Builder id(Long val) {
            id = val;
            return this;
        }

        public Builder user(User val) {
            user = val;
            return this;
        }

        public Builder profiles(Set<Profile> val) {
            profiles = val;
            return this;
        }

        public Profile build() {
            return new Profile(this);
        }
    }
}