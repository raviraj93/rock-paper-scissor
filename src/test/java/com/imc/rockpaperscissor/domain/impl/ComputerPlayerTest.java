package com.imc.rockpaperscissor.domain.impl;

import com.imc.rockpaperscissor.config.RandomProvider;
import com.imc.rockpaperscissor.domain.GameMove;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ComputerPlayerTest {
    @Test
    void testMakeMove() {
        String playerName = "Computer";
        RandomProvider mockedRandom =  mock(RandomProvider.class);

        ComputerPlayer computerPlayer = new ComputerPlayer(playerName, mockedRandom);

        when(mockedRandom.nextInt(anyInt())).thenReturn(1); // Assuming a fixed index for simplicity

        GameMove result = computerPlayer.makeMove();

        assertEquals(GameMove.values()[1], result);
    }
}
