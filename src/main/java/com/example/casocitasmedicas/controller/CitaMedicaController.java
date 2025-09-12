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

    //// LISTAR CITAS
    // Lista todas las citas con service
    @GetMapping
    public List<CitaMedica> getAll() {
        return citaService.listarTodas();
    }

    ////BUSCAR CITAS POR ID
    // Lista solo una
    @GetMapping("/citationid/{id}")
    public ResponseEntity<CitaMedica> getOneCitationById(@PathVariable Long id) {
        return citaService.buscarCitaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    ////BUSCAR CITAS POR TIPO DE CITA
    // Lista varias
    @GetMapping("/citationtype/{tipoCita}")
    public List<CitaMedica> buscarPorTipo(@PathVariable String tipoCita) {
        return citaService.buscarPorTipo(tipoCita);
    }

    //// PARA CREAR CITA
    // Crea uno
    @PostMapping
    public CitaMedica createNewCitation(@RequestBody CitaMedica citaMedica){
        Paciente paciente = pacienteService.buscarCitaPorId(citaMedica.getPaciente().getId())
                .orElseGet(() -> pacienteService.guardar(citaMedica.getPaciente()));
        citaMedica.setPaciente(paciente);
        return ResponseEntity.ok(citaService.saveCitation(citaMedica)).getBody();
    }

    //// ACTUALIZAR UNA CITA
    // Actualiza uno
    @PutMapping("/citationupdate/{id}")
    public CitaMedica UpdateCitation(@PathVariable Long id,
                          @RequestBody CitaMedica citaMedica){
        return this.citaService.UpdateOneCitation(id,citaMedica);
    }

    //// ELIMINAR UNA CITA
    // Elimina unop
    @DeleteMapping("/citationdelete/{id}")
    public ResponseEntity<Void> deleteCitation(@PathVariable Long id) {
        citaService.deleteOneCitation(id);
        return ResponseEntity.noContent().build();
    }

    //  DTO save
    @PostMapping("/dto")
    public ResponseEntity<CitaMedicaDTO> crearConDTO(@RequestBody CitaMedicaDTO dto) {
        CitaMedicaDTO saved = citaService.guardarDesdeDTO(dto);
        return ResponseEntity.ok(saved);
    }


}
