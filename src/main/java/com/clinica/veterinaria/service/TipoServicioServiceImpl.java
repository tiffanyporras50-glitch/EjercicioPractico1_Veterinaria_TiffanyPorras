/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinica.veterinaria.service;
import com.clinica.veterinaria.domain.TipoServicio;
import com.clinica.veterinaria.repository.ServicioRepository;
import com.clinica.veterinaria.repository.TipoServicioRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author tiffanyporrasmarin
 */
@Service
public class TipoServicioServiceImpl implements TipoServicioService {

    private final TipoServicioRepository tipoServicioRepository;
    private final ServicioRepository servicioRepository;

    public TipoServicioServiceImpl(TipoServicioRepository tipoServicioRepository,
                                   ServicioRepository servicioRepository) {
        this.tipoServicioRepository = tipoServicioRepository;
        this.servicioRepository = servicioRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TipoServicio> listarTipos() {
        return tipoServicioRepository.findAllByOrderByNombreAsc();
    }

    @Override
    @Transactional(readOnly = true)
    public TipoServicio buscarTipoPorId(Integer id) {
        return tipoServicioRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void guardarTipo(TipoServicio tipoServicio) {
        tipoServicioRepository.save(tipoServicio);
    }

    @Override
    @Transactional
    public boolean eliminarTipo(Integer id) {
        if (servicioRepository.existsByTipoServicioId(id)) {
            return false; // No se puede: todavía tiene servicios asociados
        }
        tipoServicioRepository.deleteById(id);
        return true;
    }
}
