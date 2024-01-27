package com.imc.rockpaperscissor.strategy;

import com.imc.rockpaperscissor.domain.GameMove;
import com.imc.rockpaperscissor.response.GameResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BasicScoringStrategyTest {

    private final ScoringStrategy scoringStrategy = new BasicScoringStrategy();

    @Test
    void determineWinnerShouldReturnTieForSameMoves() {
        GameMove move = GameMove.ROCK;
        assertEquals(GameResult.TIE, scoringStrategy.determineWinner(move, move));
    }

    @Test
    void determineWinnerShouldReturnWinForRockVsScissors() {
        assertEquals(GameResult.WIN_PLAYER_1, scoringStrategy.determineWinner(GameMove.ROCK, GameMove.SCISSORS));
    }

    @Test
    void determineWinnerShouldReturnWinForScissorsVsPaper() {
        assertEquals(GameResult.WIN_PLAYER_1, scoringStrategy.determineWinner(GameMove.SCISSORS, GameMove.PAPER));
    }

    @Test
    void determineWinnerShouldReturnWinForPaperVsRock() {
        assertEquals(GameResult.WIN_PLAYER_1, scoringStrategy.determineWinner(GameMove.PAPER, GameMove.ROCK));
    }

    @Test
    void determineWinnerShouldReturnWinForScissorsVsRock() {
        assertEquals(GameResult.WIN_PLAYER_2, scoringStrategy.determineWinner(GameMove.SCISSORS, GameMove.ROCK));
    }

    @Test
    void determineWinnerShouldReturnWinForPaperVsScissors() {
        assertEquals(GameResult.WIN_PLAYER_2, scoringStrategy.determineWinner(GameMove.PAPER, GameMove.SCISSORS));
    }

    @Test
    void determineWinnerShouldReturnWinForRockVsPaper() {
        assertEquals(GameResult.WIN_PLAYER_2, scoringStrategy.determineWinner(GameMove.ROCK, GameMove.PAPER));
    }

}
