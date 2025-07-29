package es.upm.sos.practica1.model;



import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;


@Entity
@Table(name = "libros") 
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Libro extends RepresentationModel<Libro>{
    @Id
    @Schema(description = "ISBN del libro, es el id del libro", required = true, example = "978-84-16422-96-5")
    @NotNull(message = "El ISBN del libro es obligatorio y no puede ser null")
    private String isbn;

    @Schema(description = "Título del libro", required = true, example = "Don Quijote de la Mancha")
    @NotNull(message = "El Título del libro es obligatorio y no puede ser null")
    private String titulo;

    @Schema(description = "Autor/es del libro", required = true, example = "Miguel de Cervantes")
    @NotNull(message = "El Autor/es del libro es obligatorio y no puede ser null, mínimo tiene que haber uno")
    @ElementCollection
    private List<String> autores;

    @Schema(description = "Edición del libro", required = true, example = "1")
    private int edicion;

    @Schema(description = "Editorial del libro", required = true, example = "Editorial X")
    @NotNull(message = "No puede ser null")
    private String editorial;

    @Schema(description = "Volumen del libro", required = true, example = "1")
    private int volumen;
    
    @Schema(description = "Disponibilidad del libro", required = true, example = "true")
    private boolean disponible;


}
