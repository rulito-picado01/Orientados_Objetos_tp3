package oop2.tp3.ej1;

import java.util.ArrayList;
import java.util.List;

public class FakeRepositorioDeConcursos implements RepositorioDeConcursos {

    private final List<Concurso> concursosGuardados = new ArrayList<>();

    @Override
    public void guardar(Concurso concurso) {
        concursosGuardados.add(concurso);
    }

    public boolean fueGuardado(Concurso concurso) {
        return concursosGuardados.contains(concurso);
    }

    public List<Concurso> obtenerConcursosGuardados() {
        return new ArrayList<>(concursosGuardados);
    }
}