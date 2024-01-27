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

    public HumanPlayer(String name) {
        this.name = name;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public GameMove makeMove() {
        System.out.print("Enter your move (ROCK, PAPER, SCISSORS): ");
        String input = scanner.nextLine().toUpperCase();

        try {
            return GameMove.valueOf(input);
        } catch (IllegalArgumentException e) {
            throw new InvalidMoveException("Invalid move. Please enter ROCK, PAPER, or SCISSORS.");
        }
    }
}
