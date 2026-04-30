package com.kai.football_api.match.repository;

import com.kai.football_api.match.entity.Match;
import com.kai.football_api.match.entity.MatchStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Long> , MatchRepositoryCustom{
  List<Match> findByStatus(MatchStatus status);
}