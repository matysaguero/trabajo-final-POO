package modelo.politicas;

import java.util.ArrayList;
import java.util.List;
import modelo.Ingresantes.Ingresante;

public class Resolutor {
    private List <PoliticaFronteriza> politicas; 

    public Resolutor(List<PoliticaFronteriza> politicas) throws Exception {
        this.setPoliticas(politicas);
    }


    public void setPoliticas(List<PoliticaFronteriza> politicas) throws Exception {
        if (politicas == null) {
            throw new Exception("La lista de politicas no puede ser nula.");
        }

        List<PoliticaFronteriza> copia = new ArrayList<>(); 
        for (PoliticaFronteriza politica : politicas) {             
            if (politica == null) { 
                throw new Exception("La lista no puede contener politicas nulas.");
            }
            copia.add(politica);
        }
        this.politicas = copia; 
    }

    
    public void agregarPolitica(PoliticaFronteriza politica) throws Exception {
        if (politica == null) {
            throw new Exception("La politica no puede ser nula.");
        }
        this.politicas.add(politica);
    }

    public boolean puedeIngresar(Ingresante ingresante) throws Exception {
        if (ingresante == null) {
            throw new Exception("El ingresante no puede ser nulo.");
        }

        //Recorre todas las politicas activas.
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
    









































