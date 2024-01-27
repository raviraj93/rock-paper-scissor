package com.imc.rockpaperscissor.service;

import com.imc.rockpaperscissor.domain.GameMove;
import com.imc.rockpaperscissor.domain.Player;
import com.imc.rockpaperscissor.exception.InvalidMoveException;
import com.imc.rockpaperscissor.response.GameResult;
import com.imc.rockpaperscissor.strategy.ScoringStrategy;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GameServiceTest {

    private GameService gameService;
    private ScoringStrategy scoringStrategy;
    private ByteArrayOutputStream systemOutContent;
    private PrintStream originalSystemOut;
    private Player humanPlayer;
    private Player computerPlayer;

    @BeforeEach
    void setUp() {
        scoringStrategy = mock(ScoringStrategy.class);
        humanPlayer = mock(Player.class);
        computerPlayer = mock(Player.class);
        systemOutContent = new ByteArrayOutputStream();
        originalSystemOut = System.out;
        System.setOut(new PrintStream(systemOutContent));
        gameService = new GameService(scoringStrategy);

        when(humanPlayer.getName()).thenReturn("Alice");
        when(computerPlayer.getName()).thenReturn("Computer");
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalSystemOut);
    }

    @Test
    void playRound_shouldCallMakeMoveForComputerPlayerAndPlayer2Wins() {
        when(humanPlayer.makeMove()).thenReturn(GameMove.PAPER);
        when(computerPlayer.makeMove()).thenReturn(GameMove.ROCK);

        when(scoringStrategy.determineWinner(any(), any())).thenReturn(GameResult.WIN_PLAYER_2);

        gameService.playRound(humanPlayer, computerPlayer, 1);

        verify(computerPlayer, times(1)).makeMove();
        verify(humanPlayer, times(1)).makeMove();

        String expectedMessage = "Round 1\nComputer choose ROCK\nComputer wins!\n";
        assertEquals(expectedMessage, systemOutContent.toString());
    }



    @Test
    void playRound_shouldCallMakeMoveForComputerPlayer() {

        when(humanPlayer.makeMove()).thenReturn(GameMove.ROCK);
        when(computerPlayer.makeMove()).thenReturn(GameMove.SCISSORS);
        when(scoringStrategy.determineWinner(any(), any())).thenReturn(GameResult.WIN_PLAYER_1);

        gameService.playRound(humanPlayer, computerPlayer, 1);

        verify(computerPlayer, times(1)).makeMove();
        verify(humanPlayer, times(1)).makeMove();

        String expectedMessage = "Round 1\nComputer choose SCISSORS\nAlice wins!\n";
        assertEquals(expectedMessage, systemOutContent.toString());
    }

    @Test
    void playRound_shouldCallMakeMoveForComputerPlayerAndTie() {
        when(humanPlayer.makeMove()).thenReturn(GameMove.ROCK);
        when(computerPlayer.makeMove()).thenReturn(GameMove.ROCK);
        when(scoringStrategy.determineWinner(any(), any())).thenReturn(GameResult.TIE);

        gameService.playRound(humanPlayer, computerPlayer, 1);

        verify(computerPlayer, times(1)).makeMove();
        verify(humanPlayer, times(1)).makeMove();

        String expectedMessage = "Round 1\nComputer choose ROCK\nIt's a tie!\n";
        assertEquals(expectedMessage, systemOutContent.toString());
    }

    @Test
    void playRound_shouldHandleInvalidMoveException() {
        when(humanPlayer.makeMove()).thenThrow(new InvalidMoveException("Invalid move. Please enter ROCK, PAPER, or " +
                "SCISSORS."));
        when(computerPlayer.makeMove()).thenReturn(GameMove.ROCK);

        when(scoringStrategy.determineWinner(any(), any())).thenReturn(GameResult.WIN_PLAYER_2);

        assertThrows(InvalidMoveException.class, ()-> gameService.playRound(humanPlayer, computerPlayer, 1));
    }

}

