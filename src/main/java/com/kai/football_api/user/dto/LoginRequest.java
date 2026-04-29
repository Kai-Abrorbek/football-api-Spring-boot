package com.kai.football_api.user.dto;

import lombok.*;
import jakarta.validation.constraints.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
  @NotBlank(message = "이메일을 입력해주세요.")
  @Email(message = "이메일 형식이 아닙니다.")
  private String email;

  @NotBlank(message = "비밀번호를 입력해주세요.")
  @Size(min = 6, message = "비밀번호를 6가 이상여야 합니다.")
  private String password;
}