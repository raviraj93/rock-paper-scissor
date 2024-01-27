package com.imc.rockpaperscissor;

import com.imc.rockpaperscissor.wrapper.ScannerWrapper;
import com.imc.rockpaperscissor.domain.Player;
import com.imc.rockpaperscissor.factory.PlayerFactory;
import com.imc.rockpaperscissor.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class RockPaperScissorsApplication implements CommandLineRunner {

    private final GameService gameService;
    private final PlayerFactory humanPlayerFactory;
    private final PlayerFactory computerPlayerFactory;

    private final ScannerWrapper scannerWrapper;
    private static final String COMPUTER = "COMPUTER";


    @Autowired
    public RockPaperScissorsApplication(
            @Qualifier("humanPlayerFactory") PlayerFactory humanPlayerFactory,
            @Qualifier("computerPlayerFactory") PlayerFactory computerPlayerFactory,
            GameService gameService, @Qualifier("scannerWrapperImpl") ScannerWrapper scannerWrapper) {
        this.humanPlayerFactory = humanPlayerFactory;
        this.computerPlayerFactory = computerPlayerFactory;
        this.gameService = gameService;
        this.scannerWrapper = scannerWrapper;
    }

    public static void main(String[] args) {
        SpringApplication.run(RockPaperScissorsApplication.class, args);
    }

    @Override
    public void run(String... args) {

        String playerName = getPlayerName();
        Player humanPlayer = humanPlayerFactory.createPlayer(playerName, scannerWrapper);
        Player computerPlayer = computerPlayerFactory.createPlayer(COMPUTER, scannerWrapper);

        int numberOfRounds = getNumberOfRounds();

        gameService.playGame(humanPlayer, computerPlayer, numberOfRounds);

        System.out.println("Game ended. Thanks for playing!");
        System.exit(1);
    }

    public String getPlayerName() {
        System.out.println("Hello Player!!, Please enter your name to start the Game: ");
        return scannerWrapper.nextLine();
    }


    public int getNumberOfRounds() {
        System.out.println("Enter the number of rounds to play:");
        return Integer.parseInt(scannerWrapper.nextLine());
    }
}
