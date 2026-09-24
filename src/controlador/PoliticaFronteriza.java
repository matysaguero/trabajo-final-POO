package controlador;

import modelo.Ingresante;
import modelo.TipoCiudad;
import modelo.TipoClan;

public abstract class PoliticaFronteriza{ // Principalmente seran las pautas que regularan la dificultad del juego
    private final String fecha;
    private final TipoCiudad ciudadPermitida;
    private final TipoClan clanProhibido;
 
    public PoliticaFronteriza(String fecha, TipoCiudad ciudadPermitida, TipoClan clanProhibido){
        if (fecha == null || fecha.isBlank()) {
            throw new IllegalArgumentException("Ingrese una fecha valida.");
        }
        this.fecha = fecha;
        this.ciudadPermitida = ciudadPermitida;
        this.clanProhibido = clanProhibido; 
    }

    public String getFecha(){
        return this.fecha;
    }

    public TipoCiudad getCiudadPermitida(){
        return this.ciudadPermitida;
    }

    public TipoClan getClanProhibido(){
        return this.clanProhibido;
    }

    public abstract void mostrarDetalle();

    // NUEVA LÍNEA: Declarar el método abstracto
    public abstract boolean esValido(Ingresante ingresante); 
}
