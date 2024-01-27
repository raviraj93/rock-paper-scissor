package com.imc.rockpaperscissor.domain.impl;

import com.imc.rockpaperscissor.domain.GameMove;
import com.imc.rockpaperscissor.domain.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Random;

@AllArgsConstructor
public class ComputerPlayer implements Player {
    @Getter
    private final String name;
    private final Random random;

    @Override
    public GameMove makeMove() {
        int randomIndex = random.nextInt(GameMove.values().length);
        return GameMove.values()[randomIndex];
    }
}
