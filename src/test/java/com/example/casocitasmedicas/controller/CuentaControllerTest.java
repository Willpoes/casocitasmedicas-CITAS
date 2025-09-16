package com.example.casocitasmedicas.controller;

import com.example.casocitasmedicas.repository.CitaMedica;
import com.example.casocitasmedicas.service.CitaMedicaService;
import com.example.casocitasmedicas.service.PacienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CitaMedicaControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private CitaMedicaController citaMedicaController;

    @Mock
    private CitaMedicaService citaMedicaService;

    @Mock
    private PacienteService pacienteService;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(citaMedicaController).build();
    }

    @Test
    void obtenerCitaPorId_citaExiste_retorna200() throws Exception {
        Long id = 1L;
        CitaMedica cita = new CitaMedica();
        cita.setId(id);
        cita.setTipoCita("General");
        cita.setHoraIngreso(LocalDateTime.of(2025, 9, 12, 9, 0));

        when(citaMedicaService.buscarCitaPorId(id)).thenReturn(Optional.of(cita));

        mockMvc.perform(get("/citas/citationid/{id}", id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.tipoCita").value("General"))
                .andExpect(jsonPath("$.horaIngreso").exists());
    }
}
