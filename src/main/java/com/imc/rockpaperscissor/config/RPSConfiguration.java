package com.imc.rockpaperscissor.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

@Configuration
public class RPSConfiguration {

    @Bean
    public Random random() {
        return new Random();
    }

}
