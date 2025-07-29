package es.upm.sos.practica1.assembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import es.upm.sos.practica1.controller.UsuariosController;
import es.upm.sos.practica1.model.Usuario;

/**
 * This class extends RepresentationModelAssemblerSupport which is required for
 * Pagination.
 * It converts the Customer Entity to the Customer Model and has the code for it
 */
@Component
public class UsuarioModelAssembler extends RepresentationModelAssemblerSupport<Usuario, Usuario> {
    public UsuarioModelAssembler() {
        super(UsuariosController.class, Usuario.class);
    }

    @Override
    public Usuario toModel(Usuario entity) {

        entity.add(linkTo(methodOn(UsuariosController.class).getUsuario(entity.getMatricula())).withSelfRel());

        return entity;
    }
}