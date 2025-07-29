package es.upm.sos.practica1.exception;

public class PrestamoNoEncontradoException extends RuntimeException {
    public PrestamoNoEncontradoException(Long id) {
        super("Préstamo no encontrado con ID: " + id);
    }
    
}
