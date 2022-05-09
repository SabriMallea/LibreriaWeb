package com.web.libreria.servicios;

import com.web.libreria.entidades.Autor;
import com.web.libreria.entidades.Editorial;
import com.web.libreria.entidades.Libro;
import com.web.libreria.repositorios.LibroRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LibroServicio {

    //no hace falta inicializar esta variable
    @Autowired
    private LibroRepositorio libroRepositorio;

    @Transactional
    public Libro guardar(Long isbn, String titulo, Integer anio, Autor autor, Editorial editorial) throws Exception {

        validar(isbn, titulo, anio, autor, editorial);

        Libro libro = new Libro();
        libro.setIsbn(isbn);
        libro.setTitulo(titulo);
        libro.setAnio(anio);
        libro.setAutor(autor);
        libro.setEditorial(editorial);

        //cómo hago para que me cuente todos los ejemplares??
        //List<Libro> respuesta1 = libroRepositorio.buscarLibroPorIsbn(isbn);
        //if (respuesta1 == null) {
        //  libro.setEjemplares(1);
        //} else {
        //  Integer ejemplaresCargados = 1;
        //for (Libro libro1 : respuesta1) {
        //  ejemplaresCargados  = ejemplaresCargados + libro1.getEjemplares();
        //}
        //libro.setEjemplares(ejemplaresCargados);
        //}
        // Optional<Autor> respuesta2 = autorRepositorio.findById(autor.getId());
        //if (respuesta2.isPresent()) {
        //libro.setAutor(autor);
        //} else {
        //  throw new Exception("No se pudo asociar al autor seleccionado");
        //}
        // Optional<Editorial> respuesta3 = editorialRepositorio.findById(autor.getId());
        //if (respuesta3.isPresent()) {
        //libro.setEditorial(editorial);
        //} else {
        //  throw new Exception("No se pudo asociar a la editorial seleccionada");
        //}
        return libroRepositorio.save(libro);
    }

    @Transactional
    public void modificar(String id, Long isbn, String titulo, Integer anio,
            Autor autor, Editorial editorial) throws Exception {
        validar(isbn, titulo, anio, autor, editorial);

        Optional<Libro> respuesta = libroRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Libro libro = respuesta.get();
            libro.setIsbn(isbn);
            libro.setTitulo(titulo);
            libro.setAnio(anio);
            libro.setAutor(autor);
            libro.setEditorial(editorial);

            libroRepositorio.save(libro);
        } else {
            throw new Exception("No se ha podido modificar el libro");
        }
    }

    @Transactional
    public void darBaja(String id) throws Exception {
        Optional<Libro> respuesta = libroRepositorio.findById(id);

        if (respuesta.isPresent()) {
            //esto se usa para borrar??
            Libro libro = respuesta.get();
            libro.setAlta(false);
            //libroRepositorio.delete(respuesta.get());
        } else {
            throw new Exception("No se pudo dar de baja al libro");
        }
    }

    public List<Libro> listarLibros() {
        List<Libro> listaLibros = libroRepositorio.findAll();
        return listaLibros;
    }

    public Libro buscarXIsbn(Long isbn) throws Exception {
        if (isbn == null) {
            throw new Exception("El isbn no puede ser nulo");
        }
        Libro libro = libroRepositorio.buscarLibroPorIsbn(isbn);
        return libro;
    }

    public Libro buscarXTitulo(String titulo) throws Exception {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new Exception("El titulo del libro no puede ser nulo");
        }
        Libro libro = libroRepositorio.buscarLibroXTitulo(titulo);
        return libro;
    }

    public List<Libro> listaLibrosXAutor(String id) {
        List<Libro> listaLibrosXAutor = libroRepositorio.buscarLibroPorAutor(id);
        return listaLibrosXAutor;
    }

    public List<Libro> listaLibrosXEditorial(String id) {
        List<Libro> listaLibrosXEditorial = libroRepositorio.buscarLibroPorEditorial(id);
        return listaLibrosXEditorial;
    }

    public void validar(Long isbn, String titulo, Integer anio,
            Autor autor, Editorial editorial) throws Exception {

        if (isbn == null || isbn.toString().isEmpty()) {
            throw new Exception("El isbn no puede ser nulo");
        }

        if (titulo == null || titulo.trim().isEmpty()) {
            throw new Exception("El titulo del libro no puede ser nulo");
        }

        if (anio == null || anio < 1500) {
            throw new Exception("El año ingresado no es válido");
        }

        if (autor == null) {
            throw new Exception("El autor no puede ser nulo");
        }

        if (editorial == null) {
            throw new Exception("La editorial no puede ser nula");
        }

    }

}
