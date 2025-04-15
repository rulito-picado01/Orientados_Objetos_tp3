package oop2.tp3.ej1;

import oop2.tp3.bd.ConexionBD;
import oop2.tp3.mail.Mail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Participante {
    private String dni;
    private String nombre;
    private int puntos;

    public Participante(String dni, String nombre) {
        this.dni = dni;
        this.nombre = nombre;
        this.puntos = 0;
    }

    public void agregarPuntos(int puntos) {
        this.puntos += puntos;
    }

    public Inscripcion inscribirse(Concurso concurso) {
        Inscripcion inscripcion = concurso.inscribirParticipante(this);

        if (inscripcion != null) {
            Mail emailService = new Mail();
            emailService.enviarMail(this.getDni(), this.nombre, concurso.getNombre());
        }

        return inscripcion;
    }

    public int puntosGanados() {
        return puntos;
    }

    public String getDni() {
        return dni;
    }

    public void guardarEnBaseDeDatos() {
        String sqlCheck = "SELECT dni FROM participantes WHERE dni = ?";
        String sqlInsert = "INSERT INTO participantes (dni, nombre) VALUES (?, ?)";

        try (Connection conn = ConexionBD.conectarBDC();
             PreparedStatement checkStmt = conn.prepareStatement(sqlCheck)) {

            checkStmt.setInt(1, Integer.parseInt(this.dni));
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                System.out.println("Participante ya existe en la BD, no se vuelve a insertar.");
                return; // Ya existe, evitamos duplicado
            }

            try (PreparedStatement insertStmt = conn.prepareStatement(sqlInsert)) {
                insertStmt.setInt(1, Integer.parseInt(this.dni));
                insertStmt.setString(2, this.nombre);
                insertStmt.executeUpdate();
            }

        } catch (SQLException e) {
            System.err.println("Error al guardar participante en BD: " + e.getMessage());
            e.printStackTrace();
        }
    }

}

