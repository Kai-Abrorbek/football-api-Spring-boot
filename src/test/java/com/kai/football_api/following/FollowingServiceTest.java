package com.kai.football_api.following;

import com.kai.football_api.following.entity.Following;
import com.kai.football_api.following.repository.FollowingRepository;
import com.kai.football_api.following.service.FollowingService;
import com.kai.football_api.user.entity.User;
import com.kai.football_api.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FollowingServiceTest {

    @InjectMocks
    private FollowingService followingService;

    @Mock
    private FollowingRepository followingRepository;

    @Mock
    private UserRepository userRepository;

    private User currentUser;
    private User targetUser;

    @BeforeEach
    void setUp() {
        // 테스트마다 실행 전에 유저 세팅
        currentUser = User.builder()
                .id(1L)
                .email("kai@test.com")
                .password("encodedPassword")
                .name("Kai")
                .build();

        targetUser = User.builder()
                .id(2L)
                .email("target@test.com")
                .password("encodedPassword")
                .name("Target")
                .build();

        // SecurityContextHolder에 currentUser 이메일 넣기
        UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken("kai@test.com", null, List.of());
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    @Test
    void 팔로우_성공() {
        // given
        when(userRepository.findByEmail("kai@test.com")).thenReturn(currentUser);
        when(userRepository.findById(2L)).thenReturn(Optional.of(targetUser));
        when(followingRepository.existsByFollowerAndFollowing(currentUser, targetUser)).thenReturn(false);

        // when
        followingService.follow(2L);

        // then
        verify(followingRepository, times(1)).save(any(Following.class));
    }

    @Test
    void 팔로우_실패_이미팔로우중() {
        // given
        when(userRepository.findByEmail("kai@test.com")).thenReturn(currentUser);
        when(userRepository.findById(2L)).thenReturn(Optional.of(targetUser));
        when(followingRepository.existsByFollowerAndFollowing(currentUser, targetUser)).thenReturn(true);

        // when & then
        assertThatThrownBy(() -> followingService.follow(2L))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("이미 팔로우 중입니다.");
    }

    @Test
    void 팔로우_실패_자기자신() {
        // given
        when(userRepository.findByEmail("kai@test.com")).thenReturn(currentUser);
        when(userRepository.findById(1L)).thenReturn(Optional.of(currentUser));

        // when & then
        assertThatThrownBy(() -> followingService.follow(1L))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("자기 자신을 팔로우할 수 없습니다.");
    }

    @Test
    void 언팔로우_성공() {
        // given
        Following following = Following.builder()
                .follower(currentUser)
                .following(targetUser)
                .build();

        when(userRepository.findByEmail("kai@test.com")).thenReturn(currentUser);
        when(userRepository.findById(2L)).thenReturn(Optional.of(targetUser));
        when(followingRepository.findByFollowerAndFollowing(currentUser, targetUser))
                .thenReturn(Optional.of(following));

        // when
        followingService.unfollow(2L);

        // then
        verify(followingRepository, times(1)).delete(following);
    }
}