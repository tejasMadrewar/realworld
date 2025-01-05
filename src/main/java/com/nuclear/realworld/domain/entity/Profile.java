package com.nuclear.realworld.domain.entity;

import jakarta.persistence.*;
import lombok.*;

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
    Boolean following;
    @Id
    @Column(name = "user_id")
    @EqualsAndHashCode.Include
    private Long id;
    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}