package oop2.tp3.ej1;

import java.util.ArrayList;
import java.util.List;

public class NotificadorDeInscripcionesFake implements NotificadorDeInscripciones {
    private final List<Inscripcion> inscripcionesNotificadas = new ArrayList<>();

    @Override
    public void notificar(Participante participante, Concurso concurso) {
        // simulamos la notificación registrando los datos en una Inscripcion
        inscripcionesNotificadas.add(new Inscripcion(participante, concurso, null));
    }

    public boolean fueNotificado(Inscripcion inscripcion) {
        return inscripcionesNotificadas.stream().anyMatch(i ->
                i.getParticipante().equals(inscripcion.getParticipante()) &&
                        i.getConcurso().equals(inscripcion.getConcurso())
        );
    }
}
