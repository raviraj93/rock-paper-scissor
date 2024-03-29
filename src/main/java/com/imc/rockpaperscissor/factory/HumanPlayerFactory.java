package com.imc.rockpaperscissor.factory;

import com.imc.rockpaperscissor.wrapper.ScannerWrapper;
import com.imc.rockpaperscissor.domain.Player;
import com.imc.rockpaperscissor.domain.impl.HumanPlayer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class HumanPlayerFactory implements PlayerFactory {

    @Value("${game.max_attempts}")
    private int maxAttempts;
    @Override
    public Player createPlayer(String name, ScannerWrapper scanner) {
        System.out.printf("Hello %s !!%n", name);
        return new HumanPlayer(name, scanner, maxAttempts);
    }
}
