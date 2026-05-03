package com.kai.football_api.user.service;

import com.kai.football_api.common.PreventDuplicate;
import com.kai.football_api.jwt.JwtUtil;
import com.kai.football_api.user.dto.LoginRequest;
import com.kai.football_api.user.dto.RegisterRequest;
import com.kai.football_api.user.entity.User;
import com.kai.football_api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final BCryptPasswordEncoder passwordEncoder;
  private final JwtUtil jwtUtil;

  @PreventDuplicate
  @Transactional
  public void register(RegisterRequest request) {
    if (userRepository.existsByEmail(request.getEmail())) {
      throw new RuntimeException("이미 존재하는 이메일입니다.");
    }

    User user = User.builder()
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .name(request.getName())
        .build();

    userRepository.save(user);
  }

  @PreventDuplicate
  @Transactional
  public String login(LoginRequest request) {
    User user = userRepository.findByEmail(request.getEmail());
    if (user == null) {
      throw new RuntimeException("존재하지 않는 이메일입니다. ");
    }

    if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
      throw new RuntimeException("비밀번호가 일치하지 않습니다. ");
    }

    return jwtUtil.generateToken(user.getEmail());
  }
}