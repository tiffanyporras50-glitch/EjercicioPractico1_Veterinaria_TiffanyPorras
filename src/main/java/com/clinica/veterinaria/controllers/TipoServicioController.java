/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinica.veterinaria.controllers;
import com.clinica.veterinaria.domain.TipoServicio;
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
@RequestMapping("/tipos")
public class TipoServicioController {

    private final TipoServicioService tipoServicioService;

    public TipoServicioController(TipoServicioService tipoServicioService) {
        this.tipoServicioService = tipoServicioService;
    }

    @GetMapping
    public String listarTipos(Model model) {
        model.addAttribute("tipos", tipoServicioService.listarTipos());
        return "tipos/listado";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("tipoServicio", new TipoServicio());
        return "tipos/formulario";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Integer id, Model model,
                                          RedirectAttributes redirectAttributes) {
        TipoServicio tipo = tipoServicioService.buscarTipoPorId(id);
        if (tipo == null) {
            redirectAttributes.addFlashAttribute("tipoMensaje", "danger");
            redirectAttributes.addFlashAttribute("mensaje", "El tipo de servicio no existe.");
            return "redirect:/tipos";
        }
        model.addAttribute("tipoServicio", tipo);
        return "tipos/formulario";
    }

    @PostMapping("/guardar")
    public String guardarTipo(@ModelAttribute TipoServicio tipoServicio,
                              RedirectAttributes redirectAttributes) {
        boolean esNuevo = (tipoServicio.getId() == null);
        tipoServicioService.guardarTipo(tipoServicio);
        redirectAttributes.addFlashAttribute("tipoMensaje", "success");
        redirectAttributes.addFlashAttribute("mensaje", esNuevo
                ? "Tipo de servicio agregado correctamente."
                : "Tipo de servicio modificado correctamente.");
        return "redirect:/tipos";
    }

    @PostMapping("/eliminar")
    public String eliminarTipo(@RequestParam Integer id,
                               RedirectAttributes redirectAttributes) {
        if (tipoServicioService.eliminarTipo(id)) {
            redirectAttributes.addFlashAttribute("tipoMensaje", "success");
            redirectAttributes.addFlashAttribute("mensaje", "Tipo de servicio eliminado correctamente.");
        } else {
            redirectAttributes.addFlashAttribute("tipoMensaje", "danger");
            redirectAttributes.addFlashAttribute("mensaje",
                    "No se puede eliminar: este tipo todavía tiene servicios asociados.");
        }
        return "redirect:/tipos";
    }
}