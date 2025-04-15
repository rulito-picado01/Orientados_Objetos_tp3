package oop2.tp3.ej2;

import oop2.tp3.bd.ConexionRestauranteBD;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class Pago {
    private static final String ARCHIVO_REGISTROS = "registros_pagos.txt";
    private Pedido pedido;
    private Tarjeta tarjeta;
    private double propina;

    public Pago(Pedido pedido, Tarjeta tarjeta, int propina) {
        this.pedido = pedido;
        this.tarjeta = tarjeta;
        this.propina = propina / 100.0;
    }

    public double calcularTotal(Menu menu) {
        Map<String, Integer> costo = pedido.calcularCosto(menu);
        double descuento = tarjeta.calcularDescuento(costo);
        double subtotal = costo.get("bebidas") + costo.get("platos") - descuento;
        double total = Math.round(subtotal * (1 + propina) * 100.0) / 100.0;

        guardarRegistro(total); // 👈 esto es lo nuevo

        return total;
    }

    private void guardarRegistro(double total) {
        String fecha = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String linea = fecha + " || " + total;

        // Escribir en archivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO_REGISTROS, true))) {
            writer.write(linea);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error al guardar el registro de pago en archivo: " + e.getMessage());
        }

        // Insertar en base de datos
        try (Connection conn = ConexionRestauranteBD.conectar()) {
            String sql = "INSERT INTO pagos (mesa_id, total, fecha) VALUES (?, ?, ?)";
            var stmt = conn.prepareStatement(sql);
            stmt.setInt(1, pedido.getMesaId());
            stmt.setDouble(2, total);
            stmt.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
            stmt.executeUpdate();
        } catch (Exception e) {
            System.err.println("Error al guardar el registro de pago en la BD: " + e.getMessage());
        }
    }

    public void guardarEnBaseDeDatos(double total, int mesaId) {
        try (Connection conn = ConexionRestauranteBD.conectar()) {
            String sql = "INSERT INTO pagos (total, fecha, mesa_id) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, total);
            stmt.setDate(2, java.sql.Date.valueOf(LocalDate.now()));
            stmt.setInt(3, mesaId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al guardar pago en BD: " + e.getMessage());
        }
    }
}
