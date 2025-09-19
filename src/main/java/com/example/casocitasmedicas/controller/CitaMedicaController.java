package com.example.casocitasmedicas.controller;

import com.example.casocitasmedicas.dto.CitaMedicaDTO;
import com.example.casocitasmedicas.dto.PacienteDTO;
import com.example.casocitasmedicas.exception.BadRequestException;
import com.example.casocitasmedicas.repository.CitaMedica;
import com.example.casocitasmedicas.service.CitaMedicaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/citas")
public class CitaMedicaController {

    private final CitaMedicaService citaService;

    public CitaMedicaController(CitaMedicaService citaService) {
        this.citaService = citaService;
    }

    /// LISTAR CITAS
    // Lista todas las citas con service
    @GetMapping
    public List<CitaMedica> getAll() {
        return citaService.listarTodas();
    }

    @GetMapping("/paciente/{id}")
    public ResponseEntity<PacienteDTO> obtenerPaciente(@PathVariable Long id) {
        PacienteDTO paciente = citaService.obtenerPacienteDesdePacientes(id);
        return ResponseEntity.ok(paciente);
    }

    /// BUSCAR CITAS POR ID
    // Lista solo una
    @GetMapping("/citationid/{id}")
    public ResponseEntity<CitaMedica> getOneCitationById(@PathVariable Long id) {
        return citaService.buscarCitaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    ///BUSCAR CITAS POR TIPO DE CITA
    @GetMapping("/citationtype/{tipoCita}")
    public List<CitaMedica> buscarPorTipo(@PathVariable String tipoCita) {
        return citaService.buscarPorTipo(tipoCita);
    }

    /// Crear una nueva cita
    @PostMapping("/crear")
    public ResponseEntity<CitaMedicaDTO> crearCita(@RequestBody CitaMedicaDTO dto) {
        CitaMedicaDTO nuevaCita = citaService.guardarDesdeDTO(dto);
        System.out.println("PacienteId recibido: " + dto.getPacienteId());
        return ResponseEntity.ok(nuevaCita);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<CitaMedicaDTO> actualizarCita(
            @PathVariable Long id,
            @RequestBody CitaMedicaDTO dto) {
        CitaMedicaDTO updated = citaService.actualizarCita(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/citationdelete/{id}")
    public ResponseEntity<String> deleteCitation(@PathVariable Long id) {
        citaService.deleteOneCitation(id);
        return ResponseEntity.ok("cita eliminada correctamente con [" + id+ "]");
    }

    @PostMapping("/dto/citation")
    public ResponseEntity<CitaMedicaDTO> crearConDTO(@Valid @RequestBody CitaMedicaDTO dto) {
        if (dto.getTipoCita() == null || dto.getTipoCita().isEmpty()) {
            throw new BadRequestException("El tipo no puede estar vacío");
        }
        if (dto.getHoraIngreso() == null) {
            throw new BadRequestException("Debe existir hora de ingreso");
        }
        CitaMedicaDTO saved = citaService.guardarDesdeDTO(dto);
        return ResponseEntity.ok(saved);
    }


}
