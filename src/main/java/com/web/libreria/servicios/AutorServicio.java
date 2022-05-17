package com.web.libreria.servicios;

import com.web.libreria.entidades.Autor;
import com.web.libreria.repositorios.AutorRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AutorServicio {

    @Autowired
    private AutorRepositorio autorRepositorio;

    @Transactional
    public Autor guardar(String nombre) throws Exception {
       
        Autor autor = new Autor();
        try {
            validar(nombre);
            autor.setNombre(nombre);
            autor.setAlta(true);
        } catch (Exception e) {
            e.getMessage();
        }
        return autorRepositorio.save(autor);
    }

    @Transactional
    public void modificar(String id, String nombre) throws Exception {

        Optional<Autor> respuesta = autorRepositorio.findById(id);
            validar(nombre);
        if (respuesta.isPresent()) {
            Autor autor = respuesta.get();
            autor.setNombre(nombre);
            autorRepositorio.save(autor);
        } else {
            throw new Exception("No se pudo encontrar el autor");
        }
    }

    @Transactional
    public void darBaja(String id) throws Exception {
        Optional<Autor> respuesta = autorRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Autor autor = respuesta.get();
            autor.setAlta(false);
            autorRepositorio.deleteById(id);
        } else {
            throw new Exception("No se pudo dar de baja");
        }
    }

    public List<Autor> listarAutores() {
        List<Autor> listaAutores = autorRepositorio.findAll();
        return listaAutores;
    }

    public Autor buscarXNombre(String nombre) throws Exception {
        validar(nombre);
        Autor autor = autorRepositorio.buscarAutoresXNombre(nombre);
        return autor;
    }
    
    public Autor buscarAutorId(String id) throws Exception{
        Optional<Autor> respuesta = autorRepositorio.findById(id);
        if(respuesta.isPresent()){
        Autor autor = respuesta.get();
        return autor;
        }else{
            throw new Exception("No se pudo encontrar el autor");
        }
    }

    public void validar(String nombre) throws Exception {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new Exception("El nombre no puede ser nulo");
        
        
        }
    }
}
