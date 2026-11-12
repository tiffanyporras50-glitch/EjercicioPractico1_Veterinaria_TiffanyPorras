package com.clinica.veterinaria.controllers;
import com.clinica.veterinaria.service.ServicioService;
import java.math.BigDecimal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
/**
 * Práctica #2: consulta ampliada (consulta derivada) de servicios por rango de precio.
 *
 * @author tiffanyporrasmarin
 */
@Controller
@RequestMapping("/consultaServicios")
public class ConsultaController {

    private final ServicioService servicioService;

    public ConsultaController(ServicioService servicioService) {
        this.servicioService = servicioService;
    }

    // Muestra todos los servicios (sin filtro)
    @GetMapping("/listado")
    public String listado(Model model) {
        var lista = servicioService.listarServicios();
        model.addAttribute("servicios", lista);
        model.addAttribute("totalServicios", lista.size());
        return "consultaServicios/listado";
    }

    // Recibe el formulario y muestra solo los servicios dentro del rango de precio
    @PostMapping("/consultaDerivada")
    public String consultaDerivada(@RequestParam BigDecimal precioInf,
                                   @RequestParam BigDecimal precioSup,
                                   Model model) {
        var lista = servicioService.consultaDerivada(precioInf, precioSup);
        model.addAttribute("servicios", lista);
        model.addAttribute("totalServicios", lista.size());
        model.addAttribute("precioInf", precioInf);
        model.addAttribute("precioSup", precioSup);
        return "consultaServicios/listado";
    }
}
