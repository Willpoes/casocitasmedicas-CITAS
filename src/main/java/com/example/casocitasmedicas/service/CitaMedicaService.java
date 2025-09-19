package com.example.casocitasmedicas.service;

import com.example.casocitasmedicas.Client.PacienteClient;
import com.example.casocitasmedicas.dto.CitaMedicaDTO;
import com.example.casocitasmedicas.dto.PacienteDTO;
import com.example.casocitasmedicas.repository.CitaMedica;
import com.example.casocitasmedicas.repository.CitaMedicaRepository;
import com.example.casocitasmedicas.util.CitaMedicaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CitaMedicaService {
    private static final Logger log = LoggerFactory.getLogger(CitaMedicaService.class);
    private final CitaMedicaRepository citaRepository;
    private final PacienteClient pacienteClient;

    private CitaMedicaMapper mapper;

    public CitaMedicaService(CitaMedicaRepository citaRepository,
                             PacienteClient pacienteClient,
                             CitaMedicaMapper mapper) {
        this.citaRepository = citaRepository;
        this.pacienteClient = pacienteClient;
        this.mapper = mapper;
    }

    /// LISTAR CITAS
    /// TODAS
    public List<CitaMedica> listarTodas() {
        return citaRepository.findAll();
    }

    /// OBTENER PACIENTE
    /// Solo un paciente por id
    public PacienteDTO obtenerPacienteDesdePacientes(Long id) {
        return pacienteClient.obtenerPaciente(id);
    }

    /// BUSCAR CITA
    /// Solo una cita por id
    public Optional<CitaMedica> buscarCitaPorId(Long id) {
        return citaRepository.findById(id);
    }

    /// LISTAR CITAS POR TIPOS
    /// Por tipos
    public List<CitaMedica> buscarPorTipo(String tipoCita) {
        return citaRepository.findByTipoCitaContainingIgnoreCase(tipoCita);
    }

    ///GUARDAR NUEVA CITA
    ///
    public CitaMedica saveCitation(CitaMedica citaMedica) {
        log.info("aqui estamos agregando por service");
        return citaRepository.save(citaMedica);
    }


    public CitaMedicaDTO guardarDesdeDTO(CitaMedicaDTO dto) {
        PacienteDTO paciente = pacienteClient.obtenerPaciente(dto.getPacienteId());

        if ("DESCONOCIDO".equals(paciente.getNombre())) {
            throw new RuntimeException("No se puede crear la cita: paciente " + dto.getPacienteId());
        }

        // Guardar cita
        CitaMedica cita = mapper.toEntity(dto);
        CitaMedica saved = citaRepository.save(cita);

        return mapper.toDTO(saved);
    }

    public CitaMedicaDTO actualizarCita(Long id, CitaMedicaDTO dto) {

        CitaMedica citaExistente = citaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita médica no encontrada con id: " + id));

        PacienteDTO paciente = pacienteClient.obtenerPaciente(dto.getPacienteId());

        if ("DESCONOCIDO".equals(paciente.getNombre())) {
            throw new RuntimeException("No se puede crear la cita: paciente " + dto.getPacienteId());
        }

        citaExistente.setHoraIngreso(dto.getHoraIngreso());
        citaExistente.setHoraSalida(dto.getHoraSalida());
        citaExistente.setMedico(dto.getMedico());
        citaExistente.setTipoCita(dto.getTipoCita());
        citaExistente.setCentroMedico(dto.getCentroMedico());
        citaExistente.setPacienteId(dto.getPacienteId());

        CitaMedica updated = citaRepository.save(citaExistente);

        return mapper.toDTO(updated);
    }

    /// ELIMINAR CITA
    ///  Eliminar una cita por id
    public void deleteOneCitation(Long id) {
        CitaMedica citaMedica = citaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita meedica no encontrada con id " + id));
        citaRepository.delete(citaMedica);
    }
}
