package es.upm.sos.practica1.model;


import java.time.LocalDate;
import java.util.Set;
import org.springframework.hateoas.RepresentationModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;


@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Usuario extends RepresentationModel<Usuario> {
    @Id
    @Schema(description = "Matrícula del usuario, es el id del usuario", required = true, example = "220022")
    private String matricula;
    
    @Schema(description = "Nombre del empleado", required = true, example = "Marcos")
	@NotNull(message = "El nombre de usuario es obligatorio y no puede ser null")
    private String nombreUsuario;

    @Schema(description = "Email del usuario", required = true, example = "ajks@alumnos.upm.es")
    @Email(message = "El email debe ser una dirección de correo electrónico válida")
    private String email;

    @Schema(description = "Fecha de nacimiento del usuario", required = true, example = "1990-01-01")
    @NotNull(message = "La fecha de nacimiento es obligatoria y no puede ser null")
    private LocalDate fechaNacimiento;

    @Schema(description = "Estado del usuario: si está sancionado o no", required = true, example = "true")
    private boolean estado;

    @OneToMany(mappedBy = "usuario")
    private Set<Prestamo> prestamos;
}
