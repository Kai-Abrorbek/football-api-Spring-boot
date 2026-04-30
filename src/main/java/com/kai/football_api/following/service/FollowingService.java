package com.kai.football_api.following.service;

import com.kai.football_api.following.dto.FollowingResponse;
import com.kai.football_api.following.entity.Following;
import com.kai.football_api.following.repository.FollowingRepository;
import com.kai.football_api.user.entity.User;
import com.kai.football_api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowingService {

    private final FollowingRepository followingRepository;
    private final UserRepository userRepository;

    private User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email);
    }

    @CacheEvict(value = "following", key = "'all'")
    @Transactional
    public void follow(Long targetId) {
        User currentUser = getCurrentUser();
        User targetUser = userRepository.findById(targetId)
                .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));

        if (currentUser.getId().equals(targetId)) {
            throw new RuntimeException("자기 자신을 팔로우할 수 없습니다.");
        }

        if (followingRepository.existsByFollowerAndFollowing(currentUser, targetUser)) {
            throw new RuntimeException("이미 팔로우 중입니다.");
        }

        Following following = Following.builder()
                .follower(currentUser)
                .following(targetUser)
                .build();

        followingRepository.save(following);
    }

    @CacheEvict(value = "following", key = "'all'")
    @Transactional
    public void unfollow(Long targetId) {
        User currentUser = getCurrentUser();
        User targetUser = userRepository.findById(targetId)
                .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));

        Following following = followingRepository.findByFollowerAndFollowing(currentUser, targetUser)
                .orElseThrow(() -> new RuntimeException("팔로우 중이 아닙니다."));

        followingRepository.delete(following);
    }

    @Cacheable(value = "following", key = "'all'")
    @Transactional(readOnly = true)
    public List<FollowingResponse> getFollowings() {
        return followingRepository.findByFollower(getCurrentUser())
                .stream()
                .map(FollowingResponse::new)
                .toList();
    }

    @Cacheable(value = "follower", key = "'all'")
    @Transactional(readOnly = true)
    public List<FollowingResponse> getFollowers() {
        return followingRepository.findByFollowing(getCurrentUser())
                .stream()
                .map(FollowingResponse::new)
                .toList();
    }
}