package com.imc.rockpaperscissor.factory;

import com.imc.rockpaperscissor.wrapper.ScannerWrapper;
import com.imc.rockpaperscissor.domain.Player;

public interface PlayerFactory {
    Player createPlayer(String name, ScannerWrapper scanner);
}
