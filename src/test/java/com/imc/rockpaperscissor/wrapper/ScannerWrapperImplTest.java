package com.imc.rockpaperscissor.wrapper;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ScannerWrapperImplTest {

    @Test
    void testNextLine() {
        String input = "Raviraj\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        ScannerWrapperImpl scannerWrapper = new ScannerWrapperImpl(new Scanner(System.in));

        String result = scannerWrapper.nextLine();

        System.setIn(System.in);

        assertEquals("Raviraj", result);
    }
}
