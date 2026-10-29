/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinica.veterinaria.service;
import com.clinica.veterinaria.domain.Servicio;
import com.clinica.veterinaria.domain.TipoServicio;
import com.clinica.veterinaria.repository.CitaRepository;
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
public class ServicioServiceImpl implements ServicioService {

    private final ServicioRepository servicioRepository;
    private final TipoServicioRepository tipoServicioRepository;
    private final CitaRepository citaRepository;

    public ServicioServiceImpl(ServicioRepository servicioRepository,
                               TipoServicioRepository tipoServicioRepository,
                               CitaRepository citaRepository) {
        this.servicioRepository = servicioRepository;
        this.tipoServicioRepository = tipoServicioRepository;
        this.citaRepository = citaRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Servicio> listarServicios() {
        return servicioRepository.findAllByOrderByNombreAsc();
    }

    @Override
    @Transactional(readOnly = true)
    public Servicio buscarServicioPorId(Integer id) {
        return servicioRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void guardarServicio(Servicio servicio) {
        // El formulario solo envía el id del tipo; buscamos el tipo completo en la BD
        Integer idTipo = servicio.getTipoServicio().getId();
        TipoServicio tipo = tipoServicioRepository.findById(idTipo).orElse(null);
        servicio.setTipoServicio(tipo);
        servicioRepository.save(servicio);
    }

    @Override
    @Transactional
    public boolean eliminarServicio(Integer id) {
        if (citaRepository.existsByServicioId(id)) {
            return false; // No se puede: hay citas programadas con este servicio
        }
        servicioRepository.deleteById(id);
        return true;
    }
}
