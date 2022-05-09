package com.web.libreria.repositorios;

import com.web.libreria.entidades.Libro;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepositorio extends JpaRepository<Libro, String> {

    @Query("SELECT a FROM Libro a WHERE a.autor.id = :id")
    public List<Libro> buscarLibroPorAutor(@Param("id") String id);

    @Query("SELECT a FROM Libro a WHERE a.editorial.id = :id")
    public List<Libro> buscarLibroPorEditorial(@Param("id") String id);

    //a chequear
    @Query("SELECT a FROM Libro a WHERE a.titulo = :titulo")
    public Libro buscarLibroXTitulo(@Param("titulo") String titulo);

    @Query("SELECT a FROM Libro a WHERE a.titulo LIKE %:titulo%")
    public List<Libro> buscarLibrosXTitulo(@Param("titulo") String titulo);

    @Query("SELECT a FROM Libro a WHERE a.isbn = :isbn")
    public Libro buscarLibroPorIsbn(@Param("isbn") Long isbn);

    @Query("SELECT a FROM Libro a WHERE a.autor.nombre LIKE %:nombre%")
    public List<Libro> buscarLibrosPorAutor(@Param("nombre") String nombre);

    @Query("SELECT a FROM Libro a WHERE a.editorial.nombre LIKE %:nombre%")
    public List<Libro> buscarLibrosPorEditorial(@Param("nombre") String nombre);
}
