package com.example.casocitasmedicas.controller;

import com.example.casocitasmedicas.dto.CitaMedicaDTO;
import com.example.casocitasmedicas.repository.CitaMedica;
import com.example.casocitasmedicas.repository.Paciente;
import com.example.casocitasmedicas.service.CitaMedicaService;
import com.example.casocitasmedicas.service.PacienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/citas")
public class CitaMedicaController {

    private final CitaMedicaService citaService;
    private final PacienteService pacienteService;

    public CitaMedicaController(CitaMedicaService citaService, PacienteService pacienteService) {
        this.citaService = citaService;
        this.pacienteService = pacienteService;
    }

    // Listar citas
    @GetMapping
    public List<CitaMedica> getAll() {
        return citaService.listarTodas();
    }

    // buscar cita por ID
    @GetMapping("/{id}")
    public ResponseEntity<CitaMedica> getById(@PathVariable Long id) {
        return citaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //buscar por tipos

    //guardar cita
    @PostMapping
    public ResponseEntity<CitaMedica> crear(@RequestBody CitaMedica cita) {
        Paciente paciente = pacienteService.buscarPorId(cita.getPaciente().getId())
                .orElseGet(() -> pacienteService.guardar(cita.getPaciente()));
        cita.setPaciente(paciente);
        return ResponseEntity.ok(citaService.guardar(cita));
    }

    //  DTO save
    @PostMapping("/dto")
    public ResponseEntity<CitaMedicaDTO> crearConDTO(@RequestBody CitaMedicaDTO dto) {
        CitaMedicaDTO saved = citaService.guardarDesdeDTO(dto);
        return ResponseEntity.ok(saved);
    }

    //eliminar cita
}
