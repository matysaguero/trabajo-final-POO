package modelo;

public class Jugador{
    private String nombre;
    private int reputacionJugador;
    

    public Jugador (String nombre, int reputacionInicial){
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre del jugador debe ser obligatorio.");
        }
        if (reputacionInicial <0) {
            throw new IllegalArgumentException("La reputacion no puede ser negativa.");

        }

        this.nombre = nombre;
        this.reputacionJugador = reputacionInicial;

    }

    public String getNombre(){
        return nombre;
    }

    public int getReputacion(){
        return reputacionJugador;    
    }

    public boolean aceptar(){
        return true;
    }

    public boolean rechazar(){
        return false;
    }

    // NUEVO:
    // Jugador conserva el estado de su reputación,
    // pero GestorReputacion decide cuánto debe modificarse.
    // Sin "public": solo las clases del paquete modelo (GestorReputacion) pueden llamarlo,
    // así ninguna vista ni el controlador puede alterar la reputación por su cuenta.
    void modificarReputacion(int variacion) {

        reputacionJugador += variacion;

        if (reputacionJugador < 0) {
            reputacionJugador = 0;
        }
    }

    public boolean estaSinReputacion() {
        return reputacionJugador <= 0;
    }
}
