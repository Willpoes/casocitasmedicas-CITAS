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


    // Listar citas
    public List<CitaMedica> listarTodas() {
        return citaRepository.findAll();
    }

    public PacienteDTO obtenerPacienteDesdePacientes(Long id) {
        return pacienteClient.obtenerPaciente(id);
    }

    // buscar CITA por Iid
    public Optional<CitaMedica> buscarCitaPorId(Long id) {
        return citaRepository.findById(id);
    }

    public List<CitaMedica> buscarPorTipo(String tipoCita) {
        return citaRepository.findByTipoCitaContainingIgnoreCase(tipoCita);
    }

    public CitaMedica saveCitation(CitaMedica citaMedica) {
        log.info("aqui estamos agregando por service");
        return citaRepository.save(citaMedica);
    }


    // Crear una cita desde el DTO
    public CitaMedicaDTO guardarDesdeDTO(CitaMedicaDTO dto) {
        // 1. Verificar si el paciente existe en el microservicio Pacientes
        PacienteDTO paciente = pacienteClient.obtenerPaciente(dto.getPacienteId());
        if (paciente == null) {
            throw new RuntimeException("Paciente no encontrado en microservicio Pacientes");
        }

        // 2. Convertir el DTO a entidad
        CitaMedica cita = mapper.toEntity(dto);

        // 3. Guardar la cita en la BD local (microservicio citas)
        CitaMedica saved = citaRepository.save(cita);

        // 4. Volver a DTO y devolver
        return mapper.toDTO(saved);
    }


    public CitaMedicaDTO actualizarCita(Long id, CitaMedicaDTO dto) {
        // 1. Buscar cita
        CitaMedica citaExistente = citaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita médica no encontrada con id: " + id));

        // 2. Validar que el paciente exista en el microservicio Pacientes
        PacienteDTO paciente = pacienteClient.obtenerPaciente(dto.getPacienteId());
        if (paciente == null) {
            throw new RuntimeException("Paciente no encontrado en microservicio Pacientes");
        }

        // 3. Actualizar los campos
        citaExistente.setHoraIngreso(dto.getHoraIngreso());
        citaExistente.setHoraSalida(dto.getHoraSalida());
        citaExistente.setMedico(dto.getMedico());
        citaExistente.setTipoCita(dto.getTipoCita());
        citaExistente.setCentroMedico(dto.getCentroMedico());
        citaExistente.setPacienteId(dto.getPacienteId());

        // 4. Guardar cambios
        CitaMedica updated = citaRepository.save(citaExistente);

        // 5. Devolver DTO
        return mapper.toDTO(updated);
    }

    //// Eliminar CITA
    // Eliminar una cita por id
    public void deleteOneCitation(Long id) {
        CitaMedica citaMedica = citaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita meedica no encontrada con id " + id));
        citaRepository.delete(citaMedica);
    }
}
