package com.imc.rockpaperscissor.domain.impl;

import com.imc.rockpaperscissor.wrapper.ScannerWrapper;
import com.imc.rockpaperscissor.wrapper.ScannerWrapperImpl;
import com.imc.rockpaperscissor.domain.GameMove;
import com.imc.rockpaperscissor.exception.InvalidMoveException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HumanPlayerTest {

    private HumanPlayer humanPlayer;
    private ScannerWrapper scanner;

    @BeforeEach
    void setUp() {
        InputStream inputStream = new ByteArrayInputStream("ROCK".getBytes());
        scanner = new ScannerWrapperImpl(new Scanner(inputStream));
        humanPlayer = new HumanPlayer("Raviraj", scanner, 3);
    }

    @Test
    void testGetName() {
        String result = humanPlayer.getName();
        assertEquals("Raviraj", result);
    }

    @Test
    void testValidMove() {
        GameMove result = humanPlayer.makeMove();

        assertEquals(GameMove.ROCK, result);
    }

    @Test
    void testInvalidMove() {
        InputStream inputStream = new ByteArrayInputStream("INVALID\nROCK".getBytes());
        scanner = new ScannerWrapperImpl(new Scanner(inputStream));
        humanPlayer = new HumanPlayer("Raviraj", scanner, 3);

        GameMove result = humanPlayer.makeMove();

        assertEquals(GameMove.ROCK, result);
    }

    @Test
    void testExceedMaxAttempts() {
        InputStream inputStream = new ByteArrayInputStream("INVALID\nINVALID\nINVALID\n".getBytes());
        scanner =new ScannerWrapperImpl(new Scanner(inputStream));
        humanPlayer = new HumanPlayer("Raviraj", scanner, 2);

        assertThrows(InvalidMoveException.class, () -> humanPlayer.makeMove());
    }
}
