/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinica.veterinaria.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
/**
 *
 * @author tiffanyporrasmarin
 */
@Controller
public class InicioController {

    @GetMapping("/")
    public String mostrarInicio() {
        return "index";
    }

    @GetMapping("/consultas")
    public String mostrarConsultas() {
        return "consultas";
    }

    @PostMapping("/consultas")
    public String enviarConsulta(@RequestParam String nombre,
                                 @RequestParam String correo,
                                 @RequestParam String nombreMascota,
                                 @RequestParam String mensaje,
                                 RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("tipoMensaje", "success");
        redirectAttributes.addFlashAttribute("mensaje",
                "¡Gracias, " + nombre + "! Recibimos tu consulta sobre " + nombreMascota
                + ". Te responderemos pronto a " + correo + ".");
        return "redirect:/consultas";
    }
}