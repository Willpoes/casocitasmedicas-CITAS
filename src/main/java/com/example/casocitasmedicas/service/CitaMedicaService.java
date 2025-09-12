package com.example.casocitasmedicas.service;

import com.example.casocitasmedicas.dto.CitaMedicaDTO;
import com.example.casocitasmedicas.repository.CitaMedica;
import com.example.casocitasmedicas.repository.CitaMedicaRepository;
import com.example.casocitasmedicas.repository.Paciente;
import com.example.casocitasmedicas.repository.PacienteRepository;
import com.example.casocitasmedicas.util.CitaMedicaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CitaMedicaService {
    private static final Logger log = LoggerFactory.getLogger(CitaMedicaService.class);

    private final CitaMedicaRepository citaRepository;
    private final PacienteRepository pacienteRepository;


    private CitaMedicaMapper mapper;

    public CitaMedicaService(CitaMedicaRepository citaRepository, PacienteRepository pacienteRepository, CitaMedicaMapper mapper) {
        this.citaRepository = citaRepository;
        this.pacienteRepository = pacienteRepository;
        this.mapper = mapper;
    }

    // Listar citas
    public List<CitaMedica> listarTodas() {
        return citaRepository.findAll();
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
    public CitaMedica UpdateOneCitation(Long id, CitaMedica citaMedica) {
        CitaMedica citaMedicaFinded = citaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("cita medica no se encontro!" + id));
        citaMedicaFinded.setHoraIngreso(citaMedica.getHoraIngreso());
        citaMedicaFinded.setHoraSalida(citaMedica.getHoraSalida());
        citaMedicaFinded.setMedico(citaMedica.getMedico());
        citaMedicaFinded.setTipoCita(citaMedica.getTipoCita());
        citaMedicaFinded.setCentroMedico(citaMedica.getCentroMedico());
        citaMedicaFinded.setPaciente(citaMedica.getPaciente());

        return this.citaRepository.save(citaMedicaFinded);
    }

    //// Eliminar CITA
    // Eliminar una cita por id
    public void deleteOneCitation(Long id) {
        CitaMedica citaMedica = citaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita meedica no encontrada con id " + id));
        citaRepository.delete(citaMedica);
    }
}
