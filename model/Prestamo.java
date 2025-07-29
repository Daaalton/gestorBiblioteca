package es.upm.sos.practica1.model;

import java.time.LocalDate;
import org.springframework.hateoas.RepresentationModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;;


@Entity
@Table(name = "prestamos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Prestamo extends RepresentationModel<Prestamo>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID del préstamo", required = true, example = "1")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "matricula_usuario", referencedColumnName = "matricula")
    @Schema(description = "Matrícula del usuario que realiza el préstamo", required = true, example = "220022")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "isbn_libro", referencedColumnName = "isbn")
    @Schema(description = "ISBN del libro prestado", required = true, example = "978-84-16422-96-5")
    private Libro libro;

    @Schema(description = "Fecha de préstamo del libro", required = true, example = "15/02/2025")
    @NotNull(message = "No puede ser nulo")
    private LocalDate fechaPrestamo;

    @Schema(description = "Fecha de fin del préstamo del libro", required = true, example = "27/02/2025")
    @NotNull(message = "No puede ser nulo")
    private LocalDate fechaFin;

    @Schema(description = "Fecha de devolución del libro", required = true, example = "27/02/2025")
    private LocalDate fechaDevolucion;

    @Schema(description = "Indica si el usuario está sancionado", required = true, example = "false")
    private boolean sancionado;

}
