package oop2.tp3.ej1;
import oop2.tp3.bd.ConexionBD;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public void guardarEnBaseDeDatos() {
        String sqlCheck = "SELECT nombre FROM concursos WHERE nombre = ?";
        String sqlInsert = "INSERT INTO concursos (nombre) VALUES (?)";

        try (Connection conn = ConexionBD.conectarBDC();
             PreparedStatement checkStmt = conn.prepareStatement(sqlCheck)) {

            checkStmt.setString(1, this.nombre);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                System.out.println("Concurso ya existe en la BD, no se vuelve a insertar.");
                return;
            }

            try (PreparedStatement insertStmt = conn.prepareStatement(sqlInsert)) {
                insertStmt.setString(1, this.nombre);
                insertStmt.executeUpdate();
            }

        } catch (SQLException e) {
            System.err.println("Error al guardar concurso en BD: " + e.getMessage());
            e.printStackTrace();
        }
    }


}
