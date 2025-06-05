package oop2.tp3.ej1;

public class Participante {
    private final String dni;
    private final String nombre;
    private int puntos;

    public Participante(String dni, String nombre) {
        this.dni = dni;
        this.nombre = nombre;
        this.puntos = 0;
    }

    public void agregarPuntos(int puntos) {
        this.puntos += puntos;
    }

    public Inscripcion inscribirseEn(Concurso concurso) {
        return concurso.inscribirParticipante(this);
    }

    public void guardarEn(RepositorioDeParticipantes repositorio) {
        repositorio.guardar(this);
    }

    public int puntosGanados() {
        return puntos;
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Participante)) return false;
        Participante that = (Participante) o;
        return dni.equals(that.dni);
    }

    @Override
    public int hashCode() {
        return dni.hashCode();
    }
}