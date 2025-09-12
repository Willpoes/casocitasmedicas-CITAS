package com.example.casocitasmedicas;

import com.example.casocitasmedicas.repository.CitaMedica;
import com.example.casocitasmedicas.repository.CitaMedicaRepository;
import com.example.casocitasmedicas.repository.Paciente;
import com.example.casocitasmedicas.repository.PacienteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class CasocitasmedicasApplication {

    public static void main(String[] args) {
        SpringApplication.run(CasocitasmedicasApplication.class, args);
    }

}
