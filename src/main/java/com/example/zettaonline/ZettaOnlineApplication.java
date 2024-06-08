package com.example.zettaonline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.zettaonline")
public class ZettaOnlineApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZettaOnlineApplication.class, args);
    }

}
