package com.imc.rockpaperscissor.strategy;

import com.imc.rockpaperscissor.domain.GameMove;
import com.imc.rockpaperscissor.response.GameResult;

public interface ScoringStrategy {
    GameResult determineWinner(GameMove movePlayer1, GameMove movePlayer2);
}
