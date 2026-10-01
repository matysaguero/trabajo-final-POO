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

    public void sumaReputacion(){
        reputacionJugador++;
    }

    public void restaReputacion (){
        if (reputacionJugador > 0) {   
        reputacionJugador--;
        }
    } 

    public boolean estaSinReputacion() {
        return reputacionJugador <= 0;
    }
}
