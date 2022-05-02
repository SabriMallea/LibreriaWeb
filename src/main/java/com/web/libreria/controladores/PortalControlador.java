package com.web.libreria.controladores;

import com.web.libreria.entidades.Autor;
import com.web.libreria.entidades.Editorial;
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
@RequestMapping("/")
public class PortalControlador {

    @Autowired
    private AutorServicio autorServicio;

    @Autowired
    private EditorialServicio editorialServicio;
    
    @Autowired
    private LibroServicio libroServicio;
    
    @GetMapping("/")
    public String index() {
        return "index";
    }
 
    @GetMapping("/admLibros")
    public String admLibros() {
        return "admLibros";
    }

    @GetMapping("/admAutores")
    public String admAutor() {
        return "admAutores";
    }

    @GetMapping("/admEditoriales")
    public String admEditorial() {
        return "admEditoriales";
    }

    @PostMapping("/guardarAutor")
    public String guardarAutor(@RequestParam String nombre, ModelMap modelo) throws Exception {
        try {
            System.out.println("Nombre " + nombre);
            autorServicio.guardar(nombre);
        } catch (Exception e) {
            e.getMessage();
            return "admAutores";
        }
        
        return "exito";
    }

    @PostMapping("/guardarEditorial")
    public String guardarEditorial(@RequestParam String nombre, ModelMap modelo) throws Exception {
        try {
            System.out.println("Nombre " + nombre);
            editorialServicio.guardar(nombre);
        } catch (Exception e) {
            e.getMessage();
            return "admEditoriales";
        }
        
        return "exito";
    }
    
     @GetMapping("/admLibros/guardarLibro")
    public String autor(ModelMap modelo){
        List<Autor> autores = autorServicio.listarAutores();
        modelo.addAttribute("autor", autores);
          List<Editorial> editoriales = editorialServicio.listarEditoriales();
        modelo.addAttribute("editorial", editoriales);
        return "admLibros";
    }
    
    @PostMapping("/admLibros/guardarLibro") 
    public String guardarLibro(@RequestParam Long isbn, @RequestParam String titulo,
            @RequestParam Integer anio, @RequestParam(required=false) Autor autor, @RequestParam(required = false) Editorial editorial) throws Exception{
        try {
            libroServicio.guardar(isbn, titulo, anio, autor, editorial);
            System.out.println("Autor " + autor);
            System.out.println("Editorial " + editorial);
        } catch (Exception e) {
            e.getMessage();
            System.out.println("HAY UN ERROR SABRINA");
            return "admLibros";
        }
        return "exito";
    }
    
    
}
