package oop2.tp3.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static final String URL = "jdbc:mysql://localhost:3306/concursos_db";
    private static final String USUARIO = "root";
    private static final String CONTRASEÑA = "";

    public static Connection conectarBDC() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CONTRASEÑA);
    }
}

//hola
