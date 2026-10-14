package modelo;

public class Jugador {
    public static final int REPUTACION_MAXIMA = 10;
    private String nombre;
    private int reputacionJugador;
    
    public Jugador(String nombre, int reputacionInicial) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre del jugador debe ser obligatorio.");
        }
        if (reputacionInicial < 0 || reputacionInicial > REPUTACION_MAXIMA) {
            throw new IllegalArgumentException("La reputacion inicial debe estar entre 0 y " + REPUTACION_MAXIMA + ".");
        }
        this.nombre = nombre;
        this.reputacionJugador = reputacionInicial;
    }

    public String getNombre() {
        return nombre;
    }

    public int getReputacion() {
        return reputacionJugador;
    }

    void modificarReputacion(int variacion) {
        reputacionJugador += variacion;
        if (reputacionJugador < 0) {
            reputacionJugador = 0;
        } else if (reputacionJugador > REPUTACION_MAXIMA) {
            reputacionJugador = REPUTACION_MAXIMA;
        }
    }

    void reiniciarReputacion() {
        this.reputacionJugador = REPUTACION_MAXIMA;
    }

    public boolean estaSinReputacion() {
        return reputacionJugador <= 0;
    }
}
