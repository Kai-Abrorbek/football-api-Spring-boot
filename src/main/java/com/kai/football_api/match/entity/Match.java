package com.kai.football_api.match.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "matches")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Match {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String homeTeam;

  @Column(nullable = false)
  private String awayTeam;

  @Column(nullable = false)
  private LocalDateTime matchDate;

  private Integer homeScore;

  private Integer awayScore;

  @Enumerated(EnumType.STRING)
  private MatchStatus status;

  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @PrePersist
  public void prePersist() {
    this.createdAt = LocalDateTime.now();
  }
}
