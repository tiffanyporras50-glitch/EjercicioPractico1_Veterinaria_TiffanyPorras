/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.clinica.veterinaria.service;
import com.clinica.veterinaria.domain.Servicio;
import java.util.List;
/**
 *
 * @author tiffanyporrasmarin
 */
public interface ServicioService {

    List<Servicio> listarServicios();

    Servicio buscarServicioPorId(Integer id);

    void guardarServicio(Servicio servicio);

    boolean eliminarServicio(Integer id);
}