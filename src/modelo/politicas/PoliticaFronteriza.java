package modelo.politicas;

import modelo.Ingresantes.Ingresante;

public abstract class PoliticaFronteriza{ 
   
    public abstract boolean esValido (Ingresante ingresante); 
   
    public abstract String getMotivoRechazo();

    //Politica fronteriza deja de representar una politica especifica
    //y representa un concepto general de "regla fronteriza"
    //TODA POLITICA FRONTERIZA DEBE PODER VERIFICAR UN INGRESANTE
}
