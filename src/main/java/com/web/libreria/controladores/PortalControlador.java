package com.web.libreria.controladores;

import com.web.libreria.servicios.AutorServicio;
import com.web.libreria.servicios.EditorialServicio;
import com.web.libreria.servicios.LibroServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class PortalControlador {

    @Autowired
    private AutorServicio autorServicio;

    @Autowired
    private EditorialServicio editorialServicio;

    @Autowired
    private LibroServicio libroServicio;

    @GetMapping("")
    public String index() {
        return "index";
    }

    @GetMapping("inicioAdm")
    public String inicioAdm() {
        return "inicioAdm";
    }

    @GetMapping("inicioCliente")
    public String inicioCliente() {
        return "inicioCliente";
    }

    @GetMapping("admAutores")
    public String admAutor() {
        return "admAutores";
    }

    @GetMapping("admEditoriales")
    public String admEditorial() {
        return "admEditoriales";
    }

    @GetMapping("admLibros")
    public String admLibro() {
        return "admLibros";
    }


}
