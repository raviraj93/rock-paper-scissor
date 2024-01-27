package com.imc.rockpaperscissor;

import com.imc.rockpaperscissor.domain.Player;
import com.imc.rockpaperscissor.factory.PlayerFactory;
import com.imc.rockpaperscissor.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class RockPaperScissorsApplication implements CommandLineRunner {

    private final GameService gameService;
    private final PlayerFactory humanPlayerFactory;
    private final PlayerFactory computerPlayerFactory;
    private static final String COMPUTER = "COMPUTER";


    @Autowired
    public RockPaperScissorsApplication(
            @Qualifier("humanPlayerFactory") PlayerFactory humanPlayerFactory,
            @Qualifier("computerPlayerFactory") PlayerFactory computerPlayerFactory,
            GameService gameService) {
        this.humanPlayerFactory = humanPlayerFactory;
        this.computerPlayerFactory = computerPlayerFactory;
        this.gameService = gameService;
    }

    public static void main(String[] args) {
        SpringApplication.run(RockPaperScissorsApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);

        String playerName = getPlayerName(scanner);
        Player humanPlayer = humanPlayerFactory.createPlayer(playerName, scanner);
        Player computerPlayer = computerPlayerFactory.createPlayer(COMPUTER, scanner);

        int numberOfRounds = getNumberOfRounds(scanner);

        gameService.playGame(humanPlayer, computerPlayer, numberOfRounds);

        System.out.println("Game ended. Thanks for playing!");
        System.exit(1);
    }

    private String getPlayerName(Scanner scanner) {
        System.out.println("Hello Player!!, Please enter your name to start the Game: ");
        return scanner.nextLine();
    }


    private int getNumberOfRounds(Scanner scanner) {
        System.out.println("Enter the number of rounds to play:");
        return Integer.parseInt(scanner.nextLine());
    }
}
