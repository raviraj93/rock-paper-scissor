package com.imc.rockpaperscissor.response;

import lombok.Getter;

@Getter
public enum GameResult {
    WIN_PLAYER_1("Player 1 wins!"),
    WIN_PLAYER_2("Player 2 wins!"),
    TIE("It's a tie!");

    private final String message;

    GameResult(String message) {
        this.message = message;
    }

}

