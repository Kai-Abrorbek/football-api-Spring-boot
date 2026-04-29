package com.kai.football_api.user.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
  @NotBlank(message = "이메일 입력해주세요.")
  @Email(message = "이메일 형식이 아닙니다.")
  private String email;

  @NotBlank(message = "비밀번호를 입력해주세요.")
  @Size(min = 6, message = "비밀번호는 6자 이상여야합니다.")
  private String password;

  @NotBlank(message = "이름을 입려해주에요.")
  private String name;
}