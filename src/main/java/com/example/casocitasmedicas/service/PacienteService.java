package com.example.casocitasmedicas.service;

import com.example.casocitasmedicas.repository.Paciente;
import com.example.casocitasmedicas.repository.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PacienteService {
    private final PacienteRepository pacienteRepository;

    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public Optional<Paciente> buscarPorId(Long id) {
        return pacienteRepository.findById(id);
    }

    public Paciente guardar(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }
}
