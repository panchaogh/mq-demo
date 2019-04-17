package com.pcgrw.sbrabbit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SbrabbitReliableProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SbrabbitReliableProducerApplication.class, args);
    }

}
