package com.imc.rockpaperscissor.request;

import com.imc.rockpaperscissor.domain.Player;
import lombok.Getter;

@Getter
public class GameRequest {
    private Player player;
    private int rounds;
}
