package com.kai.football_api.match.controller;

import com.kai.football_api.match.dto.MatchRequest;
import com.kai.football_api.match.entity.Match;
import com.kai.football_api.match.entity.MatchStatus;
import com.kai.football_api.match.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matches")
@RequiredArgsConstructor
public class MatchController {

  private final MatchService matchService;

  @PostMapping
  public ResponseEntity<Match> createMatch(@RequestBody MatchRequest request) {
    return ResponseEntity.ok(matchService.createMatch(request));
  }

  @GetMapping
  public ResponseEntity<List<Match>> getAllMatches() {
    return ResponseEntity.ok(matchService.getAllMatches());
  }

  @GetMapping("/status/{status}")
  public ResponseEntity<List<Match>> getByStatus(@PathVariable MatchStatus status) {
    return ResponseEntity.ok(matchService.getMatchesByStatus(status));
  }

  @PatchMapping("/{id}/score")
  public ResponseEntity<Match> updateScore(
      @PathVariable Long id,
      @RequestParam int homeScore,
      @RequestParam int awayScore) {
    return ResponseEntity.ok(matchService.updateScore(id, homeScore, awayScore));
  }
}