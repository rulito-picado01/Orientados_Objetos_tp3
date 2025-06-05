package oop2.tp3.ej1;

import oop2.tp3.bd.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RepositorioDeParticipantesBD implements RepositorioDeParticipantes {

    @Override
    public void guardar(Participante participante) {
        String sqlCheck = "SELECT dni FROM participantes WHERE dni = ?";
        String sqlInsert = "INSERT INTO participantes (dni, nombre) VALUES (?, ?)";

        try (Connection conn = ConexionBD.conectarBDC();
             PreparedStatement checkStmt = conn.prepareStatement(sqlCheck)) {

            checkStmt.setInt(1, Integer.parseInt(participante.getDni()));
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                System.out.println("Participante ya existe en la BD, no se vuelve a insertar.");
                return;
            }

            try (PreparedStatement insertStmt = conn.prepareStatement(sqlInsert)) {
                insertStmt.setInt(1, Integer.parseInt(participante.getDni()));
                insertStmt.setString(2, participante.getNombre());
                insertStmt.executeUpdate();
            }

        } catch (SQLException e) {
            System.err.println("Error al guardar participante en BD: " + e.getMessage());
            e.printStackTrace();
        }
    }
}