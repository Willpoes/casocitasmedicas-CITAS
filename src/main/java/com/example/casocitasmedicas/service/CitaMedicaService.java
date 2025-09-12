package com.example.casocitasmedicas.service;

import com.example.casocitasmedicas.dto.CitaMedicaDTO;
import com.example.casocitasmedicas.repository.CitaMedica;
import com.example.casocitasmedicas.repository.CitaMedicaRepository;
import com.example.casocitasmedicas.repository.Paciente;
import com.example.casocitasmedicas.repository.PacienteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CitaMedicaService {
    Logger log = LoggerFactory.getLogger(CitaMedica.class);

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

    // buscar CITA por Iid
    public Optional<CitaMedica> buscarCitaPorId(Long id) {
        return citaRepository.findById(id);
    }

    // buscar CITAS por tipo
    /*public List<CitaMedica> buscarCitasPorTipos(String tipo) {
        log.info("aqui estamos retornando citas/tipo en service");
        return citaRepository.findByTipoCita(tipo);
    }*/

    public List<CitaMedica> buscarPorTipo(String tipoCita) {
        return citaRepository.findByTipoCitaContainingIgnoreCase(tipoCita);
    }

    //gaurdar citas
    /*public CitaMedica guardar(CitaMedica cita) {
        return citaRepository.save(cita);
    }*/

    public CitaMedica saveCitation(CitaMedica citaMedica) {
        log.info("aqui estamos agregando por service");
        return citaRepository.save(citaMedica);
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
