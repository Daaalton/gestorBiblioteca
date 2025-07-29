package es.upm.sos.practica1.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import es.upm.sos.practica1.service.PrestamoService;
import es.upm.sos.practica1.assembler.PrestamoModelAssembler;
import es.upm.sos.practica1.model.Prestamo;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import java.time.LocalDate;


@RestController
@RequestMapping("api/v1/prestamos")
@AllArgsConstructor
public class PrestamosController {

    private final PrestamoService prestamosService;
    private final PrestamoModelAssembler prestamosModelAssembler;
    

    @PostMapping()
    public ResponseEntity<Prestamo> nuevoPrestamo(@Valid @RequestBody Prestamo prestamo) {
        prestamosService.crearPrestamo(prestamo);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prestamo> actualizarPrestamo(@PathVariable Long id, 
        @RequestBody ( required = false)Prestamo Prestamo,
        @RequestBody ( required = false) LocalDate fechaFin) {
        if (fechaFin != null) {
            Prestamo prestamoActualizado = prestamosService.ampliarPrestamo(id, fechaFin);
            return ResponseEntity.ok(prestamosModelAssembler.toModel(prestamoActualizado));
            
        } else if (Prestamo != null) {
            Prestamo prestamoActualizado = prestamosService.actualizarFechaDevolucion(id, Prestamo.getFechaDevolucion());
            return ResponseEntity.ok(prestamosModelAssembler.toModel(prestamoActualizado));
        } else {
            throw new IllegalArgumentException("Debe proporcionar al menos una fecha de devolución o una fecha de fin para actualizar el préstamo.");
        }
        
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prestamo> getPrestamo(@PathVariable Long id) {
        Prestamo prestamo = prestamosService.obtenerPrestamoPorId(id);
        return ResponseEntity.ok(prestamosModelAssembler.toModel(prestamo));
    }






}
