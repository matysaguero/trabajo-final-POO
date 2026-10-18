package modelo;

import java.time.LocalDate;

public class Puntaje {
    private final String jugador;
    private final int reputacion;
    private final LocalDate fecha;

    public Puntaje(String jugador, int reputacion, LocalDate fecha) {
        if (jugador == null || jugador.isBlank() || reputacion < 0 || fecha == null) {
            throw new IllegalArgumentException("Jugador, reputacion y fecha son obligatorios");
        }
        this.jugador = jugador;
        this.reputacion = reputacion;
        this.fecha = fecha;
    }

    public String getJugador() { return this.jugador; }
    public int getReputacion() { return this.reputacion; }
    public LocalDate getFecha() { return this.fecha; }
}
