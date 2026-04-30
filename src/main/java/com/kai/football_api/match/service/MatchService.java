package com.kai.football_api.match.service;

import com.kai.football_api.match.dto.MatchRequest;
import com.kai.football_api.match.entity.Match;
import com.kai.football_api.match.entity.MatchStatus;
import com.kai.football_api.match.repository.MatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchService {

  private final MatchRepository matchRepository;

  public Match createMatch(MatchRequest request) {
    Match match = Match.builder()
        .homeTeam(request.getHomeTeam())
        .awayTeam(request.getAwayTeam())
        .matchDate(request.getMatchDate())
        .status(request.getStatus())
        .build();
    return matchRepository.save(match);
  }

  public List<Match> getAllMatches() {
    return matchRepository.findAll();
  }

  public List<Match> getMatchesByStatus(MatchStatus status) {
    return matchRepository.findByStatus(status);
  }

  public Match updateScore(Long id, int homeScore, int awayScore) {
    Match match = matchRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("경기를 찾을 수 없습니다."));
    match.setHomeScore(homeScore);
    match.setAwayScore(awayScore);
    return matchRepository.save(match);
  }

  public List<Match>searchMatches(String teamName, MatchStatus status){
    return  matchRepository.searchMatches(teamName, status);
  }
}