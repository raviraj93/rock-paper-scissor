package com.imc.rockpaperscissor;

import com.imc.rockpaperscissor.domain.GameMove;
import com.imc.rockpaperscissor.domain.Player;
import com.imc.rockpaperscissor.domain.impl.ComputerPlayer;
import com.imc.rockpaperscissor.domain.impl.HumanPlayer;
import com.imc.rockpaperscissor.exception.InvalidMoveException;
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

        String playerName = getPlayerName(scanner);
        Player humanPlayer = createHumanPlayer(playerName, scanner);
        Player computerPlayer = createComputerPlayer();

        int numberOfRounds = getNumberOfRounds(scanner);

        playGame(humanPlayer, computerPlayer, numberOfRounds);

        System.out.println("Game ended. Thanks for playing!");
        System.exit(1);
    }

    private String getPlayerName(Scanner scanner) {
        System.out.println("Hello Player!!, Please enter your name to start the Game: ");
        return scanner.nextLine();
    }

    private Player createHumanPlayer(String playerName, Scanner scanner) {
        System.out.printf("Hello %s !!%n", playerName);
        return new HumanPlayer(playerName, scanner, maxAttempts);
    }

    private Player createComputerPlayer() {
        return new ComputerPlayer(COMPUTER, random);
    }

    private int getNumberOfRounds(Scanner scanner) {
        System.out.println("Enter the number of rounds to play:");
        return Integer.parseInt(scanner.nextLine());
    }

    private void playGame(Player humanPlayer, Player computerPlayer, int numberOfRounds) {
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

            GameResult result = gameService.playRound(userMove, computerMove);
            System.out.println(result.getMessage(humanPlayer.getName(), computerPlayer.getName()));
        }
    }
}
