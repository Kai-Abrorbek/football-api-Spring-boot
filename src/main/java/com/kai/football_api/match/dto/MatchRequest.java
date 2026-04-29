package com.kai.football_api.match.dto;

import com.kai.football_api.match.entity.MatchStatus;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MatchRequest {
  private String homeTeam;
  private String awayTeam;
  private LocalDateTime matchDate;
  private MatchStatus status;
}