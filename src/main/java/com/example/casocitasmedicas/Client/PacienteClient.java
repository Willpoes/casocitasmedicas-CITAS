package com.example.casocitasmedicas.Client;

import com.example.casocitasmedicas.dto.PacienteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "MicroPacientesV1", fallback = PacienteClientFallback.class)
public interface PacienteClient {


    @GetMapping("/pacientes/{id}")
    PacienteDTO obtenerPaciente(@PathVariable("id") Long id);

}
