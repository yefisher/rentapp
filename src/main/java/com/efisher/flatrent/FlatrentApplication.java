package com.efisher.flatrent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class FlatrentApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlatrentApplication.class, args);
    }

}
