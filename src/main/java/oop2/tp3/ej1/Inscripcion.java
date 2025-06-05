package oop2.tp3.ej1;
import oop2.tp3.bd.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class Inscripcion {
    private final Participante participante;
    private final Concurso concurso;
    private final LocalDate fecha;

    public Inscripcion(Participante participante, Concurso concurso, LocalDate fecha) {
        this.participante = participante;
        this.concurso = concurso;
        this.fecha = fecha;
    }

    public boolean esDe(Participante unParticipante) {
        return this.participante.equals(unParticipante);
    }

    public void notificarCon(NotificadorDeInscripciones notificador) {
        notificador.notificar(this.participante, this.concurso);
    }

    public void registrarEn(RepositorioDeInscripciones repositorio) {
        repositorio.guardar(this);
    }

    // Métodos de acceso solo si realmente los necesitás
    public Participante getParticipante() {
        return participante;
    }

    public Concurso getConcurso() {
        return concurso;
    }

    public LocalDate getFecha() {
        return fecha;
    }



}