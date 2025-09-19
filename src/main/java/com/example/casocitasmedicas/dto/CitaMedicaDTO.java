package com.example.casocitasmedicas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;

public class CitaMedicaDTO {
    private LocalDateTime horaIngreso;
    private LocalDateTime horaSalida;
    private String medico;

    @NotBlank(message = "El tipo de cita no puede estar vacío")
    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$",
            message = "El tipo de cita debe contener solo letras")
    private String tipoCita;
    //private String tipoCita;

    private String centroMedico;
    private Long pacienteId;

    ///
    private PacienteDTO paciente;

    public CitaMedicaDTO() {
    }

    public LocalDateTime getHoraIngreso() {
        return horaIngreso;
    }

    public void setHoraIngreso(LocalDateTime horaIngreso) {
        this.horaIngreso = horaIngreso;
    }

    public LocalDateTime getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(LocalDateTime horaSalida) {
        this.horaSalida = horaSalida;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }

    public String getTipoCita() {
        return tipoCita;
    }

    public void setTipoCita(String tipoCita) {
        this.tipoCita = tipoCita;
    }

    public String getCentroMedico() {
        return centroMedico;
    }

    public void setCentroMedico(String centroMedico) {
        this.centroMedico = centroMedico;
    }

    public Long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }

    ///
    public PacienteDTO getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteDTO paciente) {
        this.paciente = paciente;
    }
}
