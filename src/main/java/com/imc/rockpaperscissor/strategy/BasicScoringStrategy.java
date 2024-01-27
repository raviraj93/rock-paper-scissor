package com.imc.rockpaperscissor.strategy;

import com.imc.rockpaperscissor.domain.GameMove;
import com.imc.rockpaperscissor.response.GameResult;

public class BasicScoringStrategy implements ScoringStrategy {

    @Override
    public GameResult determineWinner(GameMove movePlayer1, GameMove movePlayer2) {
        int result = (movePlayer1.getValue() - movePlayer2.getValue() + 3) % 3;

        return switch (result) {
            case 0 -> GameResult.TIE;
            case 1 -> GameResult.WIN_PLAYER_1;
            case 2 -> GameResult.WIN_PLAYER_2;
            default -> throw new IllegalStateException("Invalid result: " + result);
        };
    }
}


