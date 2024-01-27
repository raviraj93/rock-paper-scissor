package com.imc.rockpaperscissor.factory;

import com.imc.rockpaperscissor.config.RandomProvider;
import com.imc.rockpaperscissor.wrapper.ScannerWrapper;
import com.imc.rockpaperscissor.domain.Player;
import com.imc.rockpaperscissor.domain.impl.ComputerPlayer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ComputerPlayerFactory implements PlayerFactory {

    private final RandomProvider randomProvider;
    @Override
    public Player createPlayer(String name, ScannerWrapper scanner) {
        return new ComputerPlayer(name, randomProvider);
    }
}
