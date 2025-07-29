package es.upm.sos.practica1.repository;

import es.upm.sos.practica1.model.Prestamo;
import es.upm.sos.practica1.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {

    List<Prestamo> findByUsuarioAndFechaDevolucionIsNull(Usuario usuario);

    List<Prestamo> findByUsuarioAndFechaDevolucionIsNotNull(Usuario usuario);

    List<Prestamo> findByUsuarioAndFechaPrestamoBetween(Usuario usuario, LocalDate desde, LocalDate hasta);

    Page<Prestamo> findByUsuario(String matricula, Pageable pageable);
}

