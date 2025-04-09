package oop2.tp3.ej2;

import java.util.HashMap;
import java.util.Map;

public class Pedido {
    private final int mesaId;
    private final Map<String, Map<String, Integer>> items;
    private boolean confirmado;

    public Pedido(int mesaId) {
        this.mesaId = mesaId;
        this.items = new HashMap<>();
        this.items.put("bebidas", new HashMap<>());
        this.items.put("platos", new HashMap<>());
        this.confirmado = false;
    }

    public void agregarItem(String categoria, String nombre, int cantidad, Menu menu) {
        if (confirmado) {
            throw new IllegalStateException("El pedido ya ha sido confirmado y no se puede modificar.");
        }
        int precio = menu.getPrecio(categoria, nombre);
        if (precio > 0) {
            items.get(categoria).merge(nombre, cantidad, Integer::sum);
        }
    }

    public void confirmarPedido() {
        this.confirmado = true;
    }

    public Map<String, Integer> calcularCosto(Menu menu) {
        Map<String, Integer> costo = new HashMap<>();
        costo.put("bebidas", 0);
        costo.put("platos", 0);
        for (String categoria : items.keySet()) {
            for (Map.Entry<String, Integer> entry : items.get(categoria).entrySet()) {
                costo.put(categoria, costo.get(categoria) + menu.getPrecio(categoria, entry.getKey()) * entry.getValue());
            }
        }
        return costo;
    }
}
