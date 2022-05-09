package com.web.libreria.repositorios;

import com.web.libreria.entidades.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepositorio extends JpaRepository <Autor, String>{
  
    @Query("SELECT a FROM Autor a WHERE a.nombre = :nombre")
    public Autor buscarAutoresXNombre(@Param("nombre") String nombre);
  
}
