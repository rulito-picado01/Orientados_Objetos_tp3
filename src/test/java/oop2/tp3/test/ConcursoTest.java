package oop2.tp3.test;

import oop2.tp3.ej1.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ConcursoTest {

    @Test
    public void inscribeParticipanteCorrectamenteYAsignaPuntos() {
        // Arrange
        FakeRepositorioDeConcursos repoConcursos = new FakeRepositorioDeConcursos();
        RepositorioDeInscripcionesFake repoInscripciones = new RepositorioDeInscripcionesFake();
        NotificadorDeInscripcionesFake notificador = new NotificadorDeInscripcionesFake();

        LocalDate hoy = LocalDate.now();
        Concurso concurso = new Concurso("Literatura", hoy, hoy.plusDays(2),
                repoConcursos, repoInscripciones, notificador);

        Participante participante = new Participante("12345678", "Juan Pérez");

        // Act
        Inscripcion inscripcion = concurso.inscribirParticipante(participante);

        // Assert
        assertNotNull(inscripcion);
        assertEquals(10, participante.puntosGanados());
        assertTrue(concurso.estaInscripto(participante));
        assertTrue(repoInscripciones.fueGuardada(inscripcion));
        assertTrue(notificador.fueNotificado(inscripcion));
    }

    @Test
    public void noPermiteInscripcionFueraDeFecha() {
        // Arrange
        LocalDate inicio = LocalDate.now().minusDays(5);
        LocalDate fin = LocalDate.now().minusDays(1);
        Concurso concurso = new Concurso("Arte", inicio, fin,
                new FakeRepositorioDeConcursos(),
                new RepositorioDeInscripcionesFake(),
                new NotificadorDeInscripcionesFake());

        Participante participante = new Participante("98765432", "María López");

        // Act
        Inscripcion inscripcion = concurso.inscribirParticipante(participante);

        // Assert
        assertNull(inscripcion);
        assertEquals(0, participante.puntosGanados());
        assertFalse(concurso.estaInscripto(participante));
    }
}