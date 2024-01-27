package com.imc.rockpaperscissor.response;

import lombok.Getter;

public enum GameResult {
    WIN_PLAYER_1,
    WIN_PLAYER_2,
    TIE;

    public String getMessage(String playerName1, String playerName2) {
        return switch (this) {
            case WIN_PLAYER_1 -> String.format("%s wins!", playerName1);
            case WIN_PLAYER_2 -> String.format("%s wins!", playerName2);
            default -> "It's a tie!";
        };
    }
}


