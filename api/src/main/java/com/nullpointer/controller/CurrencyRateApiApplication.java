package com.nullpointer.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.nullpointer.client","com.nullpointer.servlet"})
public class CurrencyRateApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CurrencyRateApiApplication.class, args);
    }
}
