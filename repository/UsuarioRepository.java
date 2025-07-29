package es.upm.sos.practica1.repository;

import es.upm.sos.practica1.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioRepository extends JpaRepository<Usuario, String> {}

