package com.web.libreria.controladores;

import com.web.libreria.entidades.Editorial;
import com.web.libreria.servicios.EditorialServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admEditoriales")
public class EditorialControlador {

    @Autowired
    private EditorialServicio editorialServicio;

    @GetMapping("/guardarEditorial")
    public String guardarEditorial() {
        return "admEditoriales";
    }

    @PostMapping("/guardarEditorial")
    public String guardarEditorial(@RequestParam String nombre, ModelMap modelo) throws Exception {
        try {
            editorialServicio.guardar(nombre);
            modelo.put("exito", "Editorial ingresada con éxito!!");
        } catch (Exception e) {
            e.getMessage();
            modelo.put("error", "No se ha podido guardar la editorial");
        }
        return "admEditoriales";
    }

    @GetMapping("/mostrarEditoriales")
    public String mostrarEditoriales(ModelMap modelo) {
        List<Editorial> editoriales = editorialServicio.listarEditoriales();
        modelo.addAttribute("editorial", editoriales);
        return "admEditoriales";
    }

}
