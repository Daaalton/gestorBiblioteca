package es.upm.sos.practica1.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;
import es.upm.sos.practica1.assembler.LibroModelAssembler;
import es.upm.sos.practica1.model.Libro;
import es.upm.sos.practica1.service.LibroService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("api/v1/libros")
@AllArgsConstructor
public class LibrosController {
    private final LibroService libroService;
    private final PagedResourcesAssembler<Libro> pagedResourcesAssembler;
    private final LibroModelAssembler libroModelAssembler;

    @PostMapping()
    public ResponseEntity<Void> nuevoLibro(@Valid @RequestBody Libro libro) {
        libroService.crearLibro(libro);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<Libro> getLibro(@PathVariable String isbn) {
        Libro libro = libroService.obtenerLibroPorIsbn(isbn);
        return ResponseEntity.ok(libroModelAssembler.toModel(libro));
    }

    @GetMapping(value = "")
    public ResponseEntity<PagedModel<Libro>> getLibros(
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) Boolean disponible,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<Libro> libros;

        if (titulo != null && disponible != null) {
            libros = libroService.buscarLibrosPorTituloYDisp(titulo, disponible, page, size);
        } else if (titulo != null) {
            libros = libroService.buscarLibrosPorTitulo(titulo, page, size);
        } else if (disponible != null) {
            libros = libroService.buscarLibrosDisponibles(disponible, page, size);
        } else {
            libros = libroService.obtenerTodosLosLibros(page, size);
        }
        return ResponseEntity.ok(pagedResourcesAssembler.toModel(libros, libroModelAssembler));
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity<Void> eliminarLibro(@PathVariable String isbn) {
        libroService.eliminarLibro(isbn);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{isbn}")
    public ResponseEntity<Void> actualizarLibro(@PathVariable String isbn, @Valid @RequestBody Libro libro) {
        libroService.actualizarLibro(isbn, libro);
        return ResponseEntity.ok().build();
    }  
    
}
