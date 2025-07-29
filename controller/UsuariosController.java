package es.upm.sos.practica1.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.upm.sos.practica1.service.UsuarioService;
import lombok.AllArgsConstructor;

import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;
import es.upm.sos.practica1.assembler.UsuarioModelAssembler;
import es.upm.sos.practica1.model.Prestamo;
import es.upm.sos.practica1.model.Usuario;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.time.LocalDate;
import java.util.List;




@RestController
@RequestMapping("api/v1/usuarios")
@AllArgsConstructor
public class UsuariosController {

    private final UsuarioService usuarioService;
    private final PagedResourcesAssembler<Usuario> pagedResourcesAssembler;
    private final UsuarioModelAssembler usuarioModelAssembler;

    @PostMapping()
    public ResponseEntity<Void> nuevoUsuario(@Valid @RequestBody Usuario usuario) {
        usuarioService.crearUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).build();
        
    }

    @GetMapping("/{matricula}")
    public ResponseEntity<Usuario> getUsuario(@PathVariable String matricula) {
        Usuario usuario = usuarioService.obtenerUsuarioPorMatricula(matricula);
        return ResponseEntity.ok(usuarioModelAssembler.toModel(usuario));
    }

    @GetMapping(value = "")
    public ResponseEntity<PagedModel<Usuario>> getUsuarios(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Usuario> usuarios = usuarioService.obtenerTodosLosUsuarios(page, size);
        return ResponseEntity.ok(pagedResourcesAssembler.toModel(usuarios, usuarioModelAssembler));
    }

    @PutMapping("/{matrucula}")
    public ResponseEntity<Void> actualizarUsuario(@PathVariable String matricula, @Valid @RequestBody Usuario usuario) {
        usuarioService.actualizarUsuario(matricula, usuario);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{matricula}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable String matricula) {
        usuarioService.eliminarUsuario(matricula);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{matricula}/prestamos")
    public ResponseEntity<List<Prestamo>> getPrestamosActivos(@PathVariable String matricula,
           @RequestParam(required = false) LocalDate fechaInicio,
           @RequestParam(required = false) LocalDate fechaFin,
           @RequestParam(required = false, defaultValue = "false") boolean historico) {

        if (fechaInicio != null && fechaFin != null) {
            return ResponseEntity.ok(usuarioService.obtenerPrestamosUsuarioPorFechas(matricula, fechaInicio, fechaFin));
        } else if (historico) {
            return ResponseEntity.ok(usuarioService.obtenerHistoricoPrestamosUsuario(matricula));
        } else {
            return ResponseEntity.ok(usuarioService.obtenerPrestamosPorUsuario(matricula));
        }
    }

    // falta actividad
    
}
