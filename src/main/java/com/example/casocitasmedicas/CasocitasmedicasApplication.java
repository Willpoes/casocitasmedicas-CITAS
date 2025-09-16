package com.example.casocitasmedicas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.example.casocitasmedicas.Client")
public class CasocitasmedicasApplication {

    public static void main(String[] args) {
        SpringApplication.run(CasocitasmedicasApplication.class, args);
    }

}
