package com.imc.rockpaperscissor.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    void testGetName() {
        String playerName = "Alice";
        Player player = new TestPlayer(playerName);

        String result = player.getName();

        assertEquals(playerName, result);
    }

    @Test
    void testMakeMove() {
        Player player = new TestPlayer("Bob");

        GameMove result = player.makeMove();

        assertNotNull(result);
    }

    // Custom implementation of Player for testing purposes
    private static class TestPlayer implements Player {
        private final String name;

        public TestPlayer(String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public GameMove makeMove() {
            return GameMove.ROCK;
        }
    }
}
