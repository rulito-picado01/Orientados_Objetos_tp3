package oop2.tp3.ej1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Inscripcion {
    private static final String ARCHIVO_INSCRIPCIONES = "inscripciones.txt";
    private Participante participante;
    private Concurso concurso;
    private LocalDate fechaInscripcion;

    public Inscripcion(Participante participante, Concurso concurso, LocalDate fechaIncripcion) {
        this.participante = participante;
        this.concurso = concurso;
        this.fechaInscripcion = fechaIncripcion;
        guardarEnArchivo();
    }

    public Participante participante() {
        return participante;
    }

    private void guardarEnArchivo() {
        String formatoFecha = fechaInscripcion.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String linea = formatoFecha + ", " + participante.getDni() + ", " + concurso.getNombre();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO_INSCRIPCIONES, true))) {
            writer.write(linea);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error al guardar la inscripción: " + e.getMessage());
        }
    }
}
