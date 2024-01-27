package com.imc.rockpaperscissor.factory;

import com.imc.rockpaperscissor.config.RandomProvider;
import com.imc.rockpaperscissor.wrapper.ScannerWrapper;
import com.imc.rockpaperscissor.wrapper.ScannerWrapperImpl;
import com.imc.rockpaperscissor.domain.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class PlayerFactoryTest {

    @MockBean
    private RandomProvider random;

    private ScannerWrapper scanner;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        scanner = new ScannerWrapperImpl(new Scanner(System.in));
    }

    @Test
    void createPlayerShouldReturnNonNullHumanPlayer() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("someUserInput".getBytes());
        System.setIn(inputStream);

        PlayerFactory playerFactory = new HumanPlayerFactory();

        Player humanPlayer = playerFactory.createPlayer("Alice", scanner);

        assertNotNull(humanPlayer);

        System.setIn(System.in);
    }

    @Test
    void createPlayerShouldReturnNonNullComputerPlayer() {
        PlayerFactory playerFactory = new ComputerPlayerFactory(random);

        Player computerPlayer = playerFactory.createPlayer("Computer", scanner);

        assertNotNull(computerPlayer);
    }

}
