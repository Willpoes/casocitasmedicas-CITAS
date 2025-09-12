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

    /*@Bean
    CommandLineRunner initDatabase(PacienteRepository pacienteRepository,
                                   CitaMedicaRepository citaRepository) {
        return args -> {
            // Crear pacientes
            Paciente p1 = new Paciente();
            p1.setNombre("Juan Pérez");
            p1.setEdad(30);
            p1.setHistorial("Hipertensión");
            p1 = pacienteRepository.save(p1);

            Paciente p2 = new Paciente();
            p2.setNombre("María López");
            p2.setEdad(45);
            p2.setHistorial("Asma");
            p2 = pacienteRepository.save(p2);

            // Crear citas
            CitaMedica c1 = new CitaMedica();
            c1.setHoraIngreso(LocalDateTime.now().plusDays(1));
            c1.setHoraSalida(LocalDateTime.now().plusDays(1).plusHours(1));
            c1.setMedico("Dr. Ramírez");
            c1.setTipoCita("Consulta general");
            c1.setCentroMedico("Clínica Central");
            c1.setPaciente(p1);
            citaRepository.save(c1);

            CitaMedica c2 = new CitaMedica();
            c2.setHoraIngreso(LocalDateTime.now().plusDays(2));
            c2.setHoraSalida(LocalDateTime.now().plusDays(2).plusHours(2));
            c2.setMedico("Dra. Torres");
            c2.setTipoCita("Cardiología");
            c2.setCentroMedico("Hospital Nacional");
            c2.setPaciente(p2);
            citaRepository.save(c2);
        };
    }*/
}
