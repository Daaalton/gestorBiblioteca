package es.upm.sos.practica1.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.upm.sos.practica1.exception.PrestamoNoEncontradoException;
import es.upm.sos.practica1.exception.PrestamoNoPosibleException;
import es.upm.sos.practica1.model.Prestamo;
import es.upm.sos.practica1.repository.PrestamoRepository;
import es.upm.sos.practica1.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import es.upm.sos.practica1.model.Usuario;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import java.util.List;

@Service
@AllArgsConstructor
public class PrestamoService {
    
    private final PrestamoRepository prestamoRepository;
    private final UsuarioRepository usuarioRepository;

    public Prestamo crearPrestamo(Prestamo prestamo) {
        if (prestamo.getUsuario().isEstado() == true) {
            throw new PrestamoNoPosibleException(prestamo.getUsuario().getMatricula());
        }
        return prestamoRepository.save(prestamo);
    }

    public Prestamo obtenerPrestamoPorId(Long id) {
        return prestamoRepository.findById(id)
                .orElseThrow(() -> new PrestamoNoEncontradoException(id));
    }

    @Transactional
    public Prestamo actualizarFechaDevolucion(Long id, LocalDate fechaDev) {
        Prestamo prestamo = obtenerPrestamoPorId(id);
        prestamo.setFechaDevolucion(fechaDev);
        if (fechaDev.isAfter(prestamo.getFechaFin())) {
            Usuario usuario = prestamo.getUsuario();
            usuario.setEstado(true);
            usuarioRepository.save(usuario);
        }
        return prestamoRepository.save(prestamo);
    }

    @Transactional
    public Prestamo ampliarPrestamo(Long id, LocalDate nuevaFechaFin) {
        Prestamo prestamo = obtenerPrestamoPorId(id);
        if (nuevaFechaFin.isBefore(prestamo.getFechaFin())) {
            throw new PrestamoNoPosibleException("La nueva fecha de fin debe ser posterior a la actual.");
        }
        prestamo.setFechaFin(nuevaFechaFin);
        return prestamoRepository.save(prestamo);
    }


    public Page<Prestamo> obtenerPrestamosPorUsuario(String matricula, int page, int size) {
        Pageable paginable = PageRequest.of(page, size);
        return prestamoRepository.findByUsuario(matricula, paginable);
    }

    public List<Prestamo> obtenerPrestamosActivosUsuario(Usuario usuario) {
        return prestamoRepository.findByUsuarioAndFechaDevolucionIsNull(usuario);
    }

    public List<Prestamo> obtenerHistoricoPrestamosUsuario(Usuario usuario) {
        return prestamoRepository.findByUsuarioAndFechaDevolucionIsNotNull(usuario);
    }

    public List<Prestamo> obtenerPrestamosActivosPorFechas(Usuario usuario, LocalDate desde, LocalDate hasta) {
        return prestamoRepository.findByUsuarioAndFechaPrestamoBetween(usuario, desde, hasta);
    }
}
