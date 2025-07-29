package es.upm.sos.practica1.exception;

public class UsuarioNoEncontradoException extends RuntimeException {
    public UsuarioNoEncontradoException(String matricula) {
        super("Usuario no encontrado con matrícula: " + matricula);
    }
}