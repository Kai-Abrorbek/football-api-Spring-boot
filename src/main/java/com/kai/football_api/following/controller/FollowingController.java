package com.kai.football_api.following.controller;

import com.kai.football_api.following.dto.FollowingResponse;
import com.kai.football_api.following.entity.Following;
import com.kai.football_api.following.service.FollowingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/follow")
@RequiredArgsConstructor
public class FollowingController {

    private final FollowingService followingService;

    @PostMapping("/{targetId}")
    public ResponseEntity<String> follow(@PathVariable Long targetId) {
        followingService.follow(targetId);
        return ResponseEntity.ok("팔로우 성공");
    }

    @DeleteMapping("/{targetId}")
    public ResponseEntity<String> unfollow(@PathVariable Long targetId) {
        followingService.unfollow(targetId);
        return ResponseEntity.ok("언팔로우 성공");
    }

    @GetMapping("/followings")
    public ResponseEntity<List<FollowingResponse>> getFollowings() {
        return ResponseEntity.ok(followingService.getFollowings());
    }

    @GetMapping("/followers")
    public ResponseEntity<List<FollowingResponse>> getFollowers() {
        return ResponseEntity.ok(followingService.getFollowers());
    }
}