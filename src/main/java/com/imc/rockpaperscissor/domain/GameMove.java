package com.imc.rockpaperscissor.domain;

import lombok.Getter;

@Getter
public enum GameMove {
    ROCK(1),
    PAPER(2),
    SCISSORS(3);

    private final int value;

    GameMove(int value) {
        this.value = value;
    }

}
