package com.kai.football_api.match.repository;

import com.kai.football_api.match.entity.Match;
import com.kai.football_api.match.entity.MatchStatus;

import java.util.List;

public interface MatchRepositoryCustom {
    List<Match> searchMatches(String teamName, MatchStatus status);
}