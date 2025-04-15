package oop2.tp3.ej1;
import oop2.tp3.bd.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class Inscripcion {
    private Participante participante;
    private Concurso concurso;
    private LocalDate fechaInscripcion;

    public Inscripcion(Participante participante, Concurso concurso, LocalDate fechaInscripcion) {
        this.participante = participante;
        this.concurso = concurso;
        this.fechaInscripcion = fechaInscripcion;
    }

    public Participante participante() {
        return participante;
    }

    public void guardarEnBaseDeDatos() {
        String sql = "INSERT INTO inscripciones (fecha, participante_id, concurso_id) VALUES (?, ?, ?)";


        try (Connection conn = ConexionBD.conectarBDC();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, java.sql.Date.valueOf(fechaInscripcion));
            stmt.setString(2, participante.getDni()); // suponiendo que DNI es la PK del participante
            stmt.setString(3, concurso.getNombre());  // suponiendo que nombre es la PK del concurso

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al guardar inscripción en BD: " + e.getMessage());
            e.printStackTrace();
        }
    }
}