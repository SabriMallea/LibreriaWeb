package com.web.libreria.servicios;

import com.web.libreria.entidades.Editorial;
import com.web.libreria.repositorios.EditorialRepositorio;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditorialServicio {

    @Autowired
    private EditorialRepositorio editorialRepositorio;

    @Transactional
    public Editorial guardar(String nombre) throws Exception {

        validar(nombre);
        Editorial editorial = new Editorial(nombre, true);
       
        return editorialRepositorio.save(editorial);
    }

    @Transactional
    public void modificar(String id, String nombre) throws Exception {

        validar(nombre);

        Optional<Editorial> respuesta = editorialRepositorio.findById(id);

        if (respuesta.isPresent()) {
            Editorial editorial = respuesta.get();
            editorial.setNombre(nombre);

            editorialRepositorio.save(editorial);
        } else {
            throw new Exception("No se puede encontrar la editorial");
        }
    }

    @Transactional
    public void darBaja(String id) throws Exception {
        Optional<Editorial> respuesta = editorialRepositorio.findById(id);

        if (respuesta.isPresent()) {
            Editorial editorial = respuesta.get();
            editorial.setAlta(false);

            //editorialRepositorio.deleteById(id);
        } else {
            throw new Exception("No se pudo dar de baja");
        }
    }

    public List<Editorial> listarEditoriales() {
        List<Editorial> listaEditoriales = editorialRepositorio.findAll();
        return listaEditoriales;
    }
    
    public Editorial buscarXnombre(String nombre){
    Editorial editorial = editorialRepositorio.buscarEditorialesXNombre(nombre);
    return editorial;
    }

    public void validar(String nombre) throws Exception {

        if (nombre == null || nombre.trim().isEmpty()) {
            throw new Exception("El nombre no puede ser nulo");
        }
    }
}
