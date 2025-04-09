package oop2.tp3.ej2;

import java.util.HashMap;
import java.util.Map;

public class Menu {
    private final Map<String, Map<String, Integer>> items;

    public Menu() {
        items = new HashMap<>();
        items.put("bebidas", Map.of("agua", 500, "coca-cola", 1000, "jugo", 800));
        items.put("platos", Map.of("hamburguesa", 2500, "pizza", 3000, "ensalada", 2000));
    }

    public int getPrecio(String categoria, String nombre) {
        return items.getOrDefault(categoria, new HashMap<>()).getOrDefault(nombre, 0);
    }
}

