package com.imc.rockpaperscissor.factory;

import com.imc.rockpaperscissor.domain.Player;

import java.util.Scanner;

public interface PlayerFactory {
    Player createPlayer(String name, Scanner scanner);
}
