package Exceptions;

public class PeriodoInscripcionInvalidoException extends RuntimeException {
    public PeriodoInscripcionInvalidoException(String mensaje) {
        super(mensaje);
    }
}