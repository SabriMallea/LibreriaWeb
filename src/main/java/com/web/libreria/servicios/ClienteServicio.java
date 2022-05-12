package com.web.libreria.servicios;

import com.web.libreria.entidades.Cliente;
import com.web.libreria.repositorios.ClienteRepositorio;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteServicio {

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Transactional
    public Cliente guardar(Long documento, String nombre, String apellido, String telefono) throws Exception {

        validar(documento, nombre, apellido, telefono);
        Cliente cliente = new Cliente(documento, nombre, apellido, telefono);
        cliente.setAlta(true);

        return (Cliente) clienteRepositorio.save(cliente);
    }

    @Transactional
    public void modificar(String id, Long documento, String nombre, String apellido, String telefono) throws Exception {

        validar(documento, nombre, apellido, telefono);
        Optional<Cliente> respuesta = clienteRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Cliente cliente = respuesta.get();
            cliente.setDocumento(documento);
            cliente.setNombre(nombre);
            cliente.setApellido(apellido);
            cliente.setTelefono(telefono);

            clienteRepositorio.save(cliente);
        } else {
            throw new Exception("No se ha podido modificar los datos del cliente");
        }

    }

    @Transactional
    public void darBaja(String id) throws Exception {
        if (id == null || id.trim().isEmpty()) {
            throw new Exception("El id no puede ser nulo");
        }

        Optional<Cliente> respuesta = clienteRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Cliente cliente = respuesta.get();
            cliente.setAlta(false);

            clienteRepositorio.deleteById(id);
        } else {
            throw new Exception("No se pudo eliminar el cliente");
        }
    }

    public void ingresoCliente(Long documento) throws Exception{
   
    }
    
    public Cliente buscarXdni(Long documento) throws Exception {
        if (documento == null || documento < 10000000) {
            throw new Exception("El dni no es válido");
        }
        Cliente cliente = clienteRepositorio.buscarClienteXdni(documento);
        return cliente;
    }

    public void validar(Long documento, String nombre, String apellido, String telefono) throws Exception {
        if (documento == null || documento < 10000000) {
            throw new Exception("El dni ingresado no es válido");
        }

        if (nombre == null || nombre.trim().isEmpty()) {
            throw new Exception("El nombre no puede ser nulo");
        }

        if (apellido == null || apellido.trim().isEmpty()) {
            throw new Exception("El apellido no puede ser nulo");
        }

        if (telefono == null || telefono.length() < 9) {
            throw new Exception("El teléfono ingresado no es válido");
        }

    }
}
