package com.imc.rockpaperscissor.factory;

import com.imc.rockpaperscissor.domain.Player;
import com.imc.rockpaperscissor.domain.impl.ComputerPlayer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.Scanner;

@Component
@AllArgsConstructor
public class ComputerPlayerFactory implements PlayerFactory {

    private final Random random;
    @Override
    public Player createPlayer(String name, Scanner scanner) {
        return new ComputerPlayer(name, random);
    }
}
