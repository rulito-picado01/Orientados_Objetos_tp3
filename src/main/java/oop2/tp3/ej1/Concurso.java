package oop2.tp3.ej1;
import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Concurso {
    private final String nombre;
    private LocalDate fechaInicioInscripcion;
    private LocalDate fechaFinInscripcion;
    private List<Inscripcion> inscripciones;

    public Concurso(String nombre, LocalDate fechaInicio, LocalDate fechaFin) {
        this.nombre = nombre;
        this.fechaInicioInscripcion = fechaInicio;
        this.fechaFinInscripcion = fechaFin;
        this.inscripciones = new ArrayList<>();
    }

    public Inscripcion inscribirParticipante(Participante participante) {
        LocalDate fechaHoy = LocalDate.now();

        if (fechaHoy.isBefore(fechaInicioInscripcion) || fechaHoy.isAfter(fechaFinInscripcion)) {
            JOptionPane.showMessageDialog(null, "No puedes inscribirte fuera del período de inscripción.",
                    "Error de Inscripción", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        Inscripcion inscripcion = new Inscripcion(participante, this, fechaHoy);
        inscripciones.add(inscripcion);
        if (fechaHoy.equals(fechaInicioInscripcion)) {
            participante.agregarPuntos(10);
        }
        return inscripcion;
    }

    public boolean estaInscripto(Participante participante) {
        return inscripciones.stream().anyMatch(inscripcion -> inscripcion.participante().equals(participante));
    }

    public String getNombre() {
        return nombre;
    }
}
