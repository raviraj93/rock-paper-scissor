package com.imc.rockpaperscissor;

import com.imc.rockpaperscissor.domain.GameMove;
import com.imc.rockpaperscissor.domain.Player;
import com.imc.rockpaperscissor.domain.impl.ComputerPlayer;
import com.imc.rockpaperscissor.domain.impl.HumanPlayer;
import com.imc.rockpaperscissor.exception.InvalidMoveException;
import com.imc.rockpaperscissor.request.GameRequest;
import com.imc.rockpaperscissor.response.GameResult;
import com.imc.rockpaperscissor.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Random;
import java.util.Scanner;

@SpringBootApplication
public class RockPaperScissorsApplication implements CommandLineRunner {

    private final GameService gameService;

    private final Random random;

    private static final String COMPUTER = "COMPUTER";

    @Value("${game.max_attempts}")
    private int maxAttempts;

    @Autowired
    public RockPaperScissorsApplication(GameService gameService, Random random) {
        this.gameService = gameService;
        this.random = random;
    }

    public static void main(String[] args) {
        SpringApplication.run(RockPaperScissorsApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello Player!!, Please enter your name to start the Game: ");
        String playerName = scanner.nextLine();

        System.out.printf("Hello %s !!%n", playerName);
        Player humanPlayer = new HumanPlayer(playerName, scanner, maxAttempts);
        Player computerPlayer = new ComputerPlayer(COMPUTER, random);



        System.out.println("Enter the number of rounds to play:");
        int numberOfRounds = Integer.parseInt(scanner.nextLine());


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

            GameResult result = gameService.playRound(userMove, computerMove);
            System.out.println(result.getMessage(humanPlayer.getName(), computerPlayer.getName()));

        }

        System.out.println("Game ended. Thanks for playing!");
        System.exit(1);
    }
}
