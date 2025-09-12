package com.example.casocitasmedicas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CitaMedicaRepository extends JpaRepository<CitaMedica, Long> {
    //JPQL
    @Query("SELECT c FROM CitaMedica c WHERE LOWER(c.tipoCita) LIKE LOWER(CONCAT(:tipo, '%'))")
    List<CitaMedica> buscarPorTipo(@Param("tipo") String tipo);
    //List<CitaMedica> findByTipoCita(String tipoCita);
    List<CitaMedica> findByTipoCitaContainingIgnoreCase(String tipoCita);
}
