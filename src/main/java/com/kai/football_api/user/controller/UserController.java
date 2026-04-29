package com.kai.football_api.user.controller;

import com.kai.football_api.user.dto.LoginRequest;
import com.kai.football_api.user.dto.RegisterRequest;
import com.kai.football_api.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @PostMapping("/register")
  public ResponseEntity<String> register(@RequestBody @Valid RegisterRequest request) {
    userService.register(request);
    return ResponseEntity.ok("회원가입 성공");
  }

  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestBody @Valid LoginRequest request) {
    String result = userService.login(request);
    return ResponseEntity.ok(result);
  }
}