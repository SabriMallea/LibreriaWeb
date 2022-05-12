package com.web.libreria.repositorios;

import com.web.libreria.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente,String>{
    
    @Query("SELECT a FROM Cliente a WHERE a.documento = :documento")
    public Cliente buscarClienteXdni(@Param("documento") Long documento);
    
}
