package modelo; //rechaza a los ingresantes que son amenaza.

public class PoliticaIngresante extends PoliticaFronteriza {

    @Override
    public boolean esValido(Ingresante ingresante) {
        return !ingresante.esAmenaza();
    }
}