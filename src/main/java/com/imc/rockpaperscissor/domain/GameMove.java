package com.imc.rockpaperscissor.domain;

public enum GameMove {
    ROCK(1),
    PAPER(2),
    SCISSORS(3);

    private final int value;

    GameMove(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
