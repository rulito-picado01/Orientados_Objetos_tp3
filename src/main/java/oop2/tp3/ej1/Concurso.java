package oop2.tp3.ej1;
import Exceptions.PeriodoInscripcionInvalidoException;

import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Concurso {
    private final String nombre;
    private final LocalDate fechaInicioInscripcion;
    private final LocalDate fechaFinInscripcion;
    private final List<Inscripcion> inscripciones;
    private final RepositorioDeConcursos repositorio;
    private final RepositorioDeInscripciones repositorioDeInscripciones;
    private final NotificadorDeInscripciones notificador;

    public Concurso(String nombre,
                    LocalDate fechaInicio,
                    LocalDate fechaFin,
                    RepositorioDeConcursos repositorio,
                    RepositorioDeInscripciones repositorioDeInscripciones,
                    NotificadorDeInscripciones notificador) {
        this.nombre = nombre;
        this.fechaInicioInscripcion = fechaInicio;
        this.fechaFinInscripcion = fechaFin;
        this.repositorio = repositorio;
        this.repositorioDeInscripciones = repositorioDeInscripciones;
        this.notificador = notificador;
        this.inscripciones = new ArrayList<>();
    }

    public Inscripcion inscribirParticipante(Participante participante) {
        LocalDate hoy = LocalDate.now();

        if (!estaDentroDelPeriodo(hoy)) {
            JOptionPane.showMessageDialog(null, "No puedes inscribirte fuera del período de inscripción.",
                    "Error de Inscripción", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        if (estaInscripto(participante)) {
            return obtenerInscripcionDe(participante);
        }

        Inscripcion inscripcion = new Inscripcion(participante, this, hoy);
        inscripciones.add(inscripcion);

        if (esPrimerDiaDeInscripcion(hoy)) {
            participante.agregarPuntos(10);
        }

        inscripcion.registrarEn(repositorioDeInscripciones);
        inscripcion.notificarCon(notificador);

        return inscripcion;
    }

    private boolean estaDentroDelPeriodo(LocalDate fecha) {
        return !(fecha.isBefore(fechaInicioInscripcion) || fecha.isAfter(fechaFinInscripcion));
    }

    private boolean esPrimerDiaDeInscripcion(LocalDate fecha) {
        return fecha.equals(fechaInicioInscripcion);
    }

    public boolean estaInscripto(Participante participante) {
        return inscripciones.stream().anyMatch(i -> i.esDe(participante));
    }

    private Inscripcion obtenerInscripcionDe(Participante participante) {
        return inscripciones.stream()
                .filter(i -> i.esDe(participante))
                .findFirst()
                .orElse(null);
    }

    public String getNombre() {
        return nombre;
    }

    public List<Inscripcion> getInscripciones() {
        return new ArrayList<>(inscripciones);
    }

    public void guardarEnBaseDeDatos() {
        repositorio.guardar(this);
    }
}
