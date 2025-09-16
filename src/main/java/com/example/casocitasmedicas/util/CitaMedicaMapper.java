package com.example.casocitasmedicas.util;

import com.example.casocitasmedicas.dto.CitaMedicaDTO;
import com.example.casocitasmedicas.repository.CitaMedica;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CitaMedicaMapper {
    CitaMedicaMapper INSTANCIA = Mappers.getMapper(CitaMedicaMapper.class);

    @Mapping(source = "pacienteId", target = "paciente.id")
    CitaMedica toEntity(CitaMedicaDTO dto);

    @Mapping(source = "paciente.id", target = "pacienteId")
    CitaMedicaDTO toDTO(CitaMedica entity);
}