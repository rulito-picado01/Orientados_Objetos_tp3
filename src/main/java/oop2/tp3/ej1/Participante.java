package oop2.tp3.ej1;

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

        return inscripcion;
    }

    public int puntosGanados() {
        return puntos;
    }

    public String getDni() {
        return dni;
    }
}
