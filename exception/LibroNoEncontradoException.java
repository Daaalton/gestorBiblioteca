package es.upm.sos.practica1.exception;

public class LibroNoEncontradoException extends RuntimeException {
    public LibroNoEncontradoException(String isbn) {
        super("Libro no encontrado con isbn: " + isbn);
    }
}