package oop2.tp3.ej2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO_REGISTROS, true))) {
            writer.write(linea);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error al guardar el registro de pago: " + e.getMessage());
        }
    }
}
