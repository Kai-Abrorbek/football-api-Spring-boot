package com.kai.football_api.user;

import com.kai.football_api.user.dto.RegisterRequest;
import com.kai.football_api.user.entity.User;
import com.kai.football_api.user.repository.UserRepository;
import com.kai.football_api.user.service.UserService;
import com.kai.football_api.jwt.JwtUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @Mock
    private JwtUtil jwtUtil;

    @Test
    void 회원가입_성공() {
        // given
        RegisterRequest request = new RegisterRequest("kai@test.com", "123456", "Kai");
        when(userRepository.existsByEmail(request.getEmail())).thenReturn(false);
        when(passwordEncoder.encode(request.getPassword())).thenReturn("encodedPassword");

        // when
        userService.register(request);

        // then
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void 회원가입_실패_이메일중복() {
        // given
        RegisterRequest request = new RegisterRequest("kai@test.com", "123456", "Kai");
        when(userRepository.existsByEmail(request.getEmail())).thenReturn(true);

        // when & then
        assertThatThrownBy(() -> userService.register(request))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("이미 존재하는 이메일입니다.");
    }
}