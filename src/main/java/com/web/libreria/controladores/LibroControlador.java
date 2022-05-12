package com.web.libreria.controladores;

import com.web.libreria.entidades.Autor;
import com.web.libreria.entidades.Editorial;
import com.web.libreria.entidades.Libro;
import com.web.libreria.servicios.AutorServicio;
import com.web.libreria.servicios.EditorialServicio;
import com.web.libreria.servicios.LibroServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admLibros")
public class LibroControlador {

    @Autowired
    private LibroServicio libroServicio;

    @Autowired
    private AutorServicio autorServicio;

    @Autowired
    private EditorialServicio editorialServicio;


    @PostMapping("/guardarLibro")
    public String guardarLibro(ModelMap modelo,@RequestParam Long isbn, @RequestParam String titulo,
            @RequestParam Integer anio, @RequestParam (required=false) Autor autor, @RequestParam (required=false)Editorial editorial) throws Exception {
        try {
            libroServicio.guardar(isbn, titulo, anio, autor, editorial);
            modelo.put("exito", "Libro ingresado con éxito!!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            modelo.put("error", "No se ha podido guardar el libro");
            System.out.println("HAY UN ERROR");
        }
        return "admLibros";
    }

      @GetMapping("/guardarLibro")
    public String guardarLibro(ModelMap modelo) {
      List<Autor> autores = autorServicio.listarAutores();
        modelo.addAttribute("autor", autores);
        List<Editorial> editoriales = editorialServicio.listarEditoriales();
        modelo.addAttribute("editorial", editoriales);
        return "admLibros";
    }
    
    @GetMapping("/mostrarLibros")
    public String mostrarLibros(ModelMap modelo) {
        modelo.addAttribute("mensajeid", "ID");
        modelo.addAttribute("mensajeisbn", "ISBN");
        modelo.addAttribute("mensajeTitulo", "Titulo");
        modelo.addAttribute("mensajeAnio", "Año");
        modelo.addAttribute("mensajeEditar", "Editar");
        List<Libro> libros = libroServicio.listarLibros();
        modelo.addAttribute("libro", libros);
        return "admLibros";
    }

}
