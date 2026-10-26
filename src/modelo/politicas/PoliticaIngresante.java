package modelo.politicas; //rechaza a los ingresantes que son amenaza.

import modelo.Ingresantes.Ingresante;

public class PoliticaIngresante extends PoliticaFronteriza {

    @Override
    public boolean esValido(Ingresante ingresante) {
        return !ingresante.esAmenaza();
    }

    @Override public String getMotivoRechazo() { return "El ingresante es una amenaza"; }
}