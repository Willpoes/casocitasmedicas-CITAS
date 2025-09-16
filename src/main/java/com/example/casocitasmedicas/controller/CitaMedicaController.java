package com.example.casocitasmedicas.controller;

import com.example.casocitasmedicas.dto.CitaMedicaDTO;
import com.example.casocitasmedicas.dto.PacienteDTO;
import com.example.casocitasmedicas.repository.CitaMedica;
import com.example.casocitasmedicas.service.CitaMedicaService;
import org.springframework.http.HttpStatus;
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
    // Crear una nueva cita
//
//    //// ACTUALIZAR UNA CITA
//    // Actualiza uno
//    @PutMapping("/citationupdate/{id}")
//    public CitaMedica UpdateCitation(@PathVariable Long id,
//                          @RequestBody CitaMedica citaMedica){
//        return this.citaService.UpdateOneCitation(id,citaMedica);
//    }
//
    //// ELIMINAR UNA CITA
    // Elimina unop
    @DeleteMapping("/citationdelete/{id}")
    public ResponseEntity<Void> deleteCitation(@PathVariable Long id) {

        citaService.deleteOneCitation(id);
        return ResponseEntity.noContent().build();
    }

    ////DTO SAVE CON MAPPER http://localhost:8080/citas/dto/citation
    //{
    //  "tipoCita": "General",
    //  "horaIngreso": "2025-09-12T09:00:00",
    //  "pacienteId": 1
    //}
    // pacienteId: null -> error 11/09 error solo con paciente id


//    @PostMapping("/dto/citation")
//    public ResponseEntity<CitaMedicaDTO> crearConDTO(@RequestBody CitaMedicaDTO dto) {
//        if (dto.getTipoCita() == null || dto.getTipoCita().isEmpty()) {
//            throw new BadRequestException("El tipo no puede estar vacío");
//        }
//        if (dto.getHoraIngreso() == null) {
//            throw new BadRequestException("Debe existir hora de ingreso");
//        }
//
//        CitaMedicaDTO saved = citaService.guardarDesdeDTO(dto);
//        return ResponseEntity.ok(saved);
//    }


}
