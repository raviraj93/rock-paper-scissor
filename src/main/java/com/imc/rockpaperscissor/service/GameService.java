package com.imc.rockpaperscissor.service;

import com.imc.rockpaperscissor.domain.GameMove;
import com.imc.rockpaperscissor.response.GameResult;
import com.imc.rockpaperscissor.strategy.ScoringStrategy;
import org.springframework.stereotype.Service;

@Service
public class GameService {
    private final ScoringStrategy scoringStrategy;

    public GameService(ScoringStrategy scoringStrategy) {
        this.scoringStrategy = scoringStrategy;
    }

    public GameResult playRound(GameMove movePlayer1, GameMove movePlayer2) {
        return scoringStrategy.determineWinner(movePlayer1, movePlayer2);
    }

}
