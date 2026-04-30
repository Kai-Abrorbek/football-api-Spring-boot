package com.kai.football_api.match.repository;

import com.kai.football_api.match.entity.Match;
import com.kai.football_api.match.entity.MatchStatus;
import com.kai.football_api.match.entity.QMatch;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MatchRepositoryImpl implements MatchRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    private final QMatch match = QMatch.match;

    @Override
    public List<Match> searchMatches(String teamName, MatchStatus status) {
        return queryFactory
                .selectFrom(match)
                .where(
                        teamNameContains(teamName),
                        statusEq(status)
                )
                .orderBy(match.matchDate.desc())
                .fetch();
    }

    private BooleanExpression teamNameContains(String teamName) {
        return teamName != null ?
                match.homeTeam.contains(teamName)
                .or(match.awayTeam.contains(teamName)) : null;
    }

    private BooleanExpression statusEq(MatchStatus status) {
        return status != null ? match.status.eq(status) : null;
    }
}