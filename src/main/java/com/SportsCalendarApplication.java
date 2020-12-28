package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class SportsCalendarApplication {

    public static void main(String[] args) {
        SpringApplication.run(SportsCalendarApplication.class, args);
    }

}
