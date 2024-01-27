package com.imc.rockpaperscissor.wrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ScannerWrapperImpl implements ScannerWrapper {
    private final Scanner scanner;

    @Autowired
    public ScannerWrapperImpl(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public String nextLine() {
        return scanner.nextLine();
    }
}
