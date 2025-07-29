package es.upm.sos.practica1.exception;

public class PrestamoNoPosibleException extends RuntimeException{
    public PrestamoNoPosibleException(String matricula) {
        super("El usuario con matricula " + matricula + " no puede realizar el préstamo porque está sancionado");
    }
}
