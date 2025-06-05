package oop2.tp3.ej1;
import java.util.ArrayList;
import java.util.List;

public class RepositorioDeInscripcionesFake implements RepositorioDeInscripciones {
    private final List<Inscripcion> inscripcionesGuardadas = new ArrayList<>();

    @Override
    public void guardar(Inscripcion inscripcion) {
        inscripcionesGuardadas.add(inscripcion);
    }

    public boolean fueGuardada(Inscripcion inscripcion) {
        return inscripcionesGuardadas.contains(inscripcion);
    }
}