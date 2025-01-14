package com.nuclear.realworld.api.controller;

import com.nuclear.realworld.api.assembler.ProfileAssembler;
import com.nuclear.realworld.api.model.profile.ProfileResponse;
import com.nuclear.realworld.api.security.AuthUtils;
import com.nuclear.realworld.api.security.authorization.CheckSecurity;
import com.nuclear.realworld.domain.entity.Profile;
import com.nuclear.realworld.domain.service.ProfileService;
import com.nuclear.realworld.domain.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/profile")
public class ProfileController {

    final private ProfileService profileService;
    final private UserService userService;
    final private AuthUtils authUtils;
    final private ProfileAssembler profileAssembler;

    public ProfileController(ProfileService profileService,
                             UserService userService, AuthUtils authUtils,
                             ProfileAssembler profileAssembler) {
        this.profileService = profileService;
        this.userService = userService;
        this.authUtils = authUtils;
        this.profileAssembler = profileAssembler;
    }

    @GetMapping("{username}")
    @CheckSecurity.Public.canRead
    public ProfileResponse getProfile(@PathVariable String username) {
        if (authUtils.isAuthenticated()) {
            Profile currentUser = userService.getCurrentUser().getProfile();
            return profileAssembler.toResponse(currentUser,
                    profileService.getByUsername(username));
        }
        return profileAssembler.toResponse(profileService.getByUsername(username));
    }

    @PostMapping("{username}/follow")
    @CheckSecurity.Protected.canManage
    public ProfileResponse followProfile(@PathVariable String username) {
        Profile toFollow = profileService.getByUsername(username);
        Profile current = userService.getCurrentUser().getProfile();

        profileService.follow(current, toFollow);
        return profileAssembler.toResponse(current, toFollow);
    }

    @DeleteMapping("{username}/follow")
    @CheckSecurity.Protected.canManage
    public ProfileResponse unfollowProfile(@PathVariable String username) {
        Profile toFollow = profileService.getByUsername(username);
        Profile current = userService.getCurrentUser().getProfile();

        profileService.unfollow(current, toFollow);
        return profileAssembler.toResponse(current, toFollow);
    }

}
