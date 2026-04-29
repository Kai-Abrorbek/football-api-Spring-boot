package com.kai.football_api.following.dto;

import com.kai.football_api.following.entity.Following;
import lombok.Getter;

@Getter
public class FollowingResponse {
    private Long id;
    private Long followerId;
    private String followerName;
    private Long followingId;
    private String followingName;

    public FollowingResponse(Following following) {
        this.id = following.getId();
        this.followerId = following.getFollower().getId();
        this.followerName = following.getFollower().getName();
        this.followingId = following.getFollowing().getId();
        this.followingName = following.getFollowing().getName();
    }
}