/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.clinica.veterinaria.service;
import com.clinica.veterinaria.domain.TipoServicio;
import java.util.List;

/**
 *
 * @author tiffanyporrasmarin
 */
public interface TipoServicioService {

    List<TipoServicio> listarTipos();

    TipoServicio buscarTipoPorId(Integer id);

    void guardarTipo(TipoServicio tipoServicio);

    boolean eliminarTipo(Integer id);
}