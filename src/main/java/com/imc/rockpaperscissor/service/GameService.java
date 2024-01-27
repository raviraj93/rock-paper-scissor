package com.imc.rockpaperscissor.service;

import com.imc.rockpaperscissor.domain.GameMove;
import com.imc.rockpaperscissor.domain.Player;
import com.imc.rockpaperscissor.exception.InvalidMoveException;
import com.imc.rockpaperscissor.response.GameResult;
import com.imc.rockpaperscissor.strategy.ScoringStrategy;
import org.springframework.stereotype.Service;

@Service
public class GameService {
    private final ScoringStrategy scoringStrategy;

    public GameService(ScoringStrategy scoringStrategy) {
        this.scoringStrategy = scoringStrategy;
    }

    public void playGame(Player humanPlayer, Player computerPlayer, int numberOfRounds) {

        for (int round = 1; round <= numberOfRounds; round++) {

            System.out.printf("Round %d%n", round);

            GameMove userMove;

            try {
                userMove = humanPlayer.makeMove();
            } catch (InvalidMoveException e) {
                System.out.println(e.getMessage());
                continue;
            }

            GameMove computerMove = computerPlayer.makeMove();
            System.out.println("Computer choose " + computerMove.name());

            GameResult result = playRound(userMove, computerMove);
            System.out.println(result.getMessage(humanPlayer.getName(), computerPlayer.getName()));
        }
    }

    public GameResult playRound(GameMove movePlayer1, GameMove movePlayer2) {
        return scoringStrategy.determineWinner(movePlayer1, movePlayer2);
    }

}
