package modelo;

import java.util.ArrayList;
import java.util.List;

public class Resolutor {
    private List <PoliticaFronteriza> politicas; 

    public Resolutor(List<PoliticaFronteriza> politicas) throws Exception {
        this.setPoliticas(politicas);
    }


    public void setPoliticas(List<PoliticaFronteriza> politicas) throws Exception {
        
        if (politicas == null) {
            throw new Exception("La lista de politicas no puede ser nula.");
        }

        List<PoliticaFronteriza> copia = new ArrayList<>(); //crea una lista nueva
        for (PoliticaFronteriza politica : politicas) { //recorre las politicas
            
            if (politica == null) { //comprueba que ninguna politica sea null
                throw new Exception("La lista no puede contener politicas nulas.");
            }
            
            copia.add(politica); //agrega dichas politicas a la copia
        }
        
        this.politicas = copia; //guarda la copia
    }

    
    public void agregarPolitica(PoliticaFronteriza politica) throws Exception {
        
        if (politica == null) {
            throw new Exception("La politica no puede ser nula.");
        }
        
        this.politicas.add(politica);
    }

    // AND encadenado de politicas: politica1 && politica2 && politica3 &&...etcetc
    // Alcanza con que una sola falle para que el resultado sea false
    public boolean puedeIngresar(Ingresante ingresante) throws Exception {
        if (ingresante == null) {
            
            throw new Exception("El ingresante no puede ser nulo.");
        }
        //NUEVO: recorre todas las politicas activas.
        //Si una sola no se cumple, el ingresente no puede pasar.
        for (PoliticaFronteriza politica : politicas) {
            if (!politica.esValido(ingresante)){
                return false;
            }
        }
        //Si cumplió todas las políticas ACTIVAS POR EL MOMENTO, puede ingresar.
        return true;
    }

}
    













































