package com.example.casocitasmedicas.Client;

import com.example.casocitasmedicas.dto.PacienteDTO;
import org.springframework.stereotype.Component;

@Component
public class PacienteClientFallback implements PacienteClient {

    @Override
    public PacienteDTO obtenerPaciente(Long id) {
        PacienteDTO paciente = new PacienteDTO();
        paciente.setId(id);
        paciente.setNombre("DESCONOCIDO");
        paciente.setTipoSangre("SERVICIO NO DISPONIBLE ");
        return paciente;
    }
}

