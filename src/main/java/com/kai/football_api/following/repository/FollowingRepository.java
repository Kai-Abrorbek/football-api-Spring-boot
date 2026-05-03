package com.kai.football_api.following.repository;

import com.kai.football_api.following.entity.Following;
import com.kai.football_api.user.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FollowingRepository extends JpaRepository<Following, Long> {
    List<Following> findByFollower(User follower);
    List<Following> findByFollowing(User following);
    Optional<Following> findByFollowerAndFollowing(User follower, User following);
    boolean existsByFollowerAndFollowing(User follower, User following);
    @Query("SELECT f FROM Following f JOIN FETCH f.follower JOIN FETCH f.following") // N+1 문제 해결사 2
    List<Following> findAllWithUsers();

    @EntityGraph(attributePaths = {"follower", "following"}) // N+1 문제 해결사 2
    List<Following> findAll();
}