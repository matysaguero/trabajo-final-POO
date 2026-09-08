package controlador;

import modelo.CiudadesPermitidas;
import modelo.ClanesProhibidos;

public abstract class PoliticaFronteriza{ // Principalmente seran las pautas que regularan la dificultad del juego
    private final String fecha;
    private final CiudadesPermitidas ciudadesPermitidas;
    private final ClanesProhibidos clanesProhibidos;
 
    public PoliticaFronteriza(String fecha, CiudadesPermitidas ciudadesPermitidas, ClanesProhibidos clanesProhibidos){
        if (fecha == null || fecha.isBlank()) {
            throw new IllegalArgumentException("Ingrese una fecha valida.");
        }
        this.fecha = fecha;
        this.ciudadesPermitidas = ciudadesPermitidas;
        this.clanesProhibidos = clanesProhibidos; 
    }

public String getFecha(){
    return this.fecha;
}

public CiudadesPermitidas getCiudadesPermitidas(){
    return this.ciudadesPermitidas;
}

public ClanesProhibidos getClanesProhibidos(){
    return this.clanesProhibidos;
}


}