package com.imc.rockpaperscissor.domain.impl;

import com.imc.rockpaperscissor.domain.GameMove;
import com.imc.rockpaperscissor.domain.Player;
import com.imc.rockpaperscissor.exception.InvalidMoveException;
import lombok.Getter;

import java.util.Scanner;

public class HumanPlayer implements Player {

    @Getter
    private String name;
    private final Scanner scanner;
    private final int maxAttempts;

    public HumanPlayer(String name, Scanner scanner, int maxAttempts) {
        this.name = name;
        this.scanner = scanner;
        this.maxAttempts = maxAttempts;
    }

    @Override
    public GameMove makeMove() {

        int attempts = 0;

        while (attempts < maxAttempts) {
            try {
                System.out.print("Enter your move (ROCK, PAPER, SCISSORS): ");
                String input = scanner.nextLine().toUpperCase();
                return GameMove.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid move. Please enter ROCK, PAPER, or SCISSORS.");
                attempts++;
            }
        }
        throw new InvalidMoveException("Exceeded maximum number of attempts for invalid moves.");
    }
}
