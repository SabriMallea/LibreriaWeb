package com.web.libreria.controladores;

import com.web.libreria.entidades.Autor;
import com.web.libreria.servicios.AutorServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admAutores")
public class AutorControlador {

    @Autowired
    private AutorServicio autorServicio;

    @GetMapping("/guardarAutor")
    public String guardarAutor() {
        return "admAutores";
    }

    @PostMapping("/guardarAutor")
    public String guardarAutor(ModelMap modelo, @RequestParam String nombre) throws Exception {
        try {
            autorServicio.guardar(nombre);
            System.out.println("Autor " + nombre);
            modelo.put("exito", "Autor ingresado con éxito!!");
        } catch (Exception e) {
            e.getMessage();
            modelo.put("error", "No se ha podido guardar el autor");
        }
        return "admAutores";
    }

    @GetMapping("/mostrarAutores")
    public String mostrarAutores(ModelMap modelo) {
        List<Autor> autores = autorServicio.listarAutores();
        modelo.addAttribute("autor", autores);
        return "admAutores";
    }

}
