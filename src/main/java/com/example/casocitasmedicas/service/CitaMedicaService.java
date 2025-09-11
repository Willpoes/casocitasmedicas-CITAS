package com.example.casocitasmedicas.service;

import com.example.casocitasmedicas.dto.CitaMedicaDTO;
import com.example.casocitasmedicas.repository.CitaMedica;
import com.example.casocitasmedicas.repository.CitaMedicaRepository;
import com.example.casocitasmedicas.repository.Paciente;
import com.example.casocitasmedicas.repository.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CitaMedicaService {
    private final CitaMedicaRepository citaRepository;
    private final PacienteRepository pacienteRepository;

    public CitaMedicaService(CitaMedicaRepository citaRepository, PacienteRepository pacienteRepository) {
        this.citaRepository = citaRepository;
        this.pacienteRepository = pacienteRepository;
    }

    // Listar citas
    public List<CitaMedica> listarTodas() {
        return citaRepository.findAll();
    }

    // buscar cita por Iid
    public Optional<CitaMedica> buscarPorId(Long id) {
        return citaRepository.findById(id);
    }

    //gaurdar citas
    public CitaMedica guardar(CitaMedica cita) {
        return citaRepository.save(cita);
    }

    //solo par ahora de inggreso, mientras guardar
    public CitaMedicaDTO guardarDesdeDTO(CitaMedicaDTO dto) {
        Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        CitaMedica cita = new CitaMedica();
        cita.setHoraIngreso(dto.getHoraIngreso());
        cita.setHoraSalida(dto.getHoraSalida());
        cita.setMedico(dto.getMedico());
        cita.setTipoCita(dto.getTipoCita());
        cita.setCentroMedico(dto.getCentroMedico());
        cita.setPaciente(paciente);

        CitaMedica saved = citaRepository.save(cita);

        CitaMedicaDTO response = new CitaMedicaDTO();
        response.setHoraIngreso(saved.getHoraIngreso());
        response.setHoraSalida(saved.getHoraSalida());
        response.setMedico(saved.getMedico());
        response.setTipoCita(saved.getTipoCita());
        response.setCentroMedico(saved.getCentroMedico());
        response.setPacienteId(saved.getPaciente().getId());
        return response;
    }

    //
}
