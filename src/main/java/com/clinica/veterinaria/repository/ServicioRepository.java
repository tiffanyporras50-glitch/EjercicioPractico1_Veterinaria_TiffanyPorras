/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.clinica.veterinaria.repository;
import com.clinica.veterinaria.domain.Servicio;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author tiffanyporrasmarin
 */
public interface ServicioRepository extends JpaRepository<Servicio, Integer> {

    List<Servicio> findAllByOrderByNombreAsc();

    boolean existsByTipoServicioId(Integer tipoServicioId);

    // Consulta ampliada (consulta derivada): servicios con precio entre un
    // mínimo y un máximo, ordenados del más barato al más caro
    List<Servicio> findByPrecioBetweenOrderByPrecioAsc(BigDecimal precioInf, BigDecimal precioSup);
}