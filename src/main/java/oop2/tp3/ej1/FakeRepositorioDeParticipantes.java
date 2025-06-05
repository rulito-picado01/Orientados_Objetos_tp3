package oop2.tp3.ej1;

import java.util.HashMap;
import java.util.Map;

public class FakeRepositorioDeParticipantes implements RepositorioDeParticipantes {

    private final Map<String, Participante> participantes = new HashMap<>();

    @Override
    public void guardar(Participante participante) {
        participantes.put(participante.getDni(), participante);
    }

    public Participante buscarPorDni(String dni) {
        return participantes.get(dni);
    }

    public boolean existe(String dni) {
        return participantes.containsKey(dni);
    }

    public int cantidad() {
        return participantes.size();
    }

    public void limpiar() {
        participantes.clear();
    }
}
