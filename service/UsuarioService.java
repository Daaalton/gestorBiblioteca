package es.upm.sos.practica1.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import es.upm.sos.practica1.model.Prestamo;
import es.upm.sos.practica1.model.Usuario;
import es.upm.sos.practica1.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import es.upm.sos.practica1.repository.PrestamoRepository;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import java.util.List;
import es.upm.sos.practica1.exception.UsuarioNoEncontradoException;

@Service
@AllArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PrestamoRepository prestamoRepository;
    
    public Usuario crearUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void eliminarUsuario(String matricula) {
        usuarioRepository.deleteById(matricula);
    }

    public Usuario obtenerUsuarioPorMatricula (String matricula) {
        return usuarioRepository.findById(matricula)
                .orElseThrow(() -> new UsuarioNoEncontradoException(matricula));
    }

    public Page<Usuario> obtenerTodosLosUsuarios(int page, int size) {
        Pageable paginable = PageRequest.of(page, size);
        return usuarioRepository.findAll(paginable);
    }

    @Transactional
    public Usuario actualizarUsuario (String matricula, Usuario usuarioActualizado) {
        Usuario usuario = obtenerUsuarioPorMatricula(matricula);
        usuario.setNombreUsuario(usuarioActualizado.getNombreUsuario());
        usuario.setEmail(usuarioActualizado.getEmail());
        usuario.setFechaNacimiento(usuarioActualizado.getFechaNacimiento());
        usuario.setEstado(usuarioActualizado.isEstado());
        return usuarioRepository.save(usuario);
    }


    public List<Prestamo> obtenerPrestamosPorUsuario(String matricula) {
        Usuario usuario = obtenerUsuarioPorMatricula(matricula);
        return prestamoRepository.findByUsuarioAndFechaDevolucionIsNull(usuario);
    }

    public List<Prestamo> obtenerPrestamosUsuarioPorFechas(String matricula, LocalDate fechaInicio, LocalDate fechaFin) {
        Usuario usuario = obtenerUsuarioPorMatricula(matricula);
        return prestamoRepository.findByUsuarioAndFechaPrestamoBetween(usuario, fechaInicio, fechaFin);
    }

    public List<Prestamo> obtenerHistoricoPrestamosUsuario(String matricula) {
        Usuario usuario = obtenerUsuarioPorMatricula(matricula);
        return prestamoRepository.findByUsuarioAndFechaDevolucionIsNotNull(usuario);
    }

    // TO DO: Actividad Usuarrio Se debe facilitar consultar rápidamente toda la información de actividad relacionada con un usuario. 
    //      Esta respuesta debe contener toda la información relativa al usuario, así como; 
    //      la lista de libros que tiene actualmente prestados ordenada por fecha de préstamos, 
    //      el historial de préstamos con los últimos 5 libros que ya ha devuelto. 
    
    

}

