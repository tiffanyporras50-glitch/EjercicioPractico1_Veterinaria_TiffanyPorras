/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinica.veterinaria.controllers;
import com.clinica.veterinaria.domain.Servicio;
import com.clinica.veterinaria.domain.TipoServicio;
import com.clinica.veterinaria.service.ServicioService;
import com.clinica.veterinaria.service.TipoServicioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
/**
 *
 * @author tiffanyporrasmarin
 */
@Controller
@RequestMapping("/servicios")
public class ServicioController {

    private final ServicioService servicioService;
    private final TipoServicioService tipoServicioService;

    public ServicioController(ServicioService servicioService,
                              TipoServicioService tipoServicioService) {
        this.servicioService = servicioService;
        this.tipoServicioService = tipoServicioService;
    }

    @GetMapping
    public String listarServicios(Model model) {
        model.addAttribute("servicios", servicioService.listarServicios());
        return "servicios/listado";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        Servicio servicio = new Servicio();
        servicio.setTipoServicio(new TipoServicio());
        model.addAttribute("servicio", servicio);
        model.addAttribute("tipos", tipoServicioService.listarTipos());
        return "servicios/formulario";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Integer id, Model model,
                                          RedirectAttributes redirectAttributes) {
        Servicio servicio = servicioService.buscarServicioPorId(id);
        if (servicio == null) {
            redirectAttributes.addFlashAttribute("tipoMensaje", "danger");
            redirectAttributes.addFlashAttribute("mensaje", "El servicio no existe.");
            return "redirect:/servicios";
        }
        model.addAttribute("servicio", servicio);
        model.addAttribute("tipos", tipoServicioService.listarTipos());
        return "servicios/formulario";
    }

    @PostMapping("/guardar")
    public String guardarServicio(@ModelAttribute Servicio servicio,
                                  RedirectAttributes redirectAttributes) {
        boolean esNuevo = (servicio.getId() == null);
        servicioService.guardarServicio(servicio);
        redirectAttributes.addFlashAttribute("tipoMensaje", "success");
        redirectAttributes.addFlashAttribute("mensaje", esNuevo
                ? "Servicio agregado correctamente."
                : "Servicio modificado correctamente.");
        return "redirect:/servicios";
    }

    @PostMapping("/eliminar")
    public String eliminarServicio(@RequestParam Integer id,
                                   RedirectAttributes redirectAttributes) {
        if (servicioService.eliminarServicio(id)) {
            redirectAttributes.addFlashAttribute("tipoMensaje", "success");
            redirectAttributes.addFlashAttribute("mensaje", "Servicio eliminado correctamente.");
        } else {
            redirectAttributes.addFlashAttribute("tipoMensaje", "danger");
            redirectAttributes.addFlashAttribute("mensaje",
                    "No se puede eliminar: hay citas programadas con este servicio.");
        }
        return "redirect:/servicios";
    }
}
