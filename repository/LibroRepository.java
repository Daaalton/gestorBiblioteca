package es.upm.sos.practica1.repository;

import es.upm.sos.practica1.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, String> {
    
    List<Libro> findByTituloContaining(String titulo);

    List<Libro> findByDisponible(Boolean disponible);

    List<Libro> findByTituloContainingAndDisponible(String titulo, Boolean disponible);

    Page<Libro> findByTituloContaining(String titulo, Pageable pageable);
    Page<Libro> findByDisponible(Boolean disponible, Pageable pageable);
    Page<Libro> findByTituloContainingAndDisponible(String titulo, Boolean disponible, Pageable pageable);
}

