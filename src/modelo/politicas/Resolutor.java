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

    // Devuelve la política exacta que falló, o null si todo está en regla.
    public PoliticaFronteriza obtenerPoliticaFallida(Ingresante ingresante) throws Exception {
        if (ingresante == null) {
            throw new Exception("El ingresante no puede ser nulo.");
        }
        for (PoliticaFronteriza politica : politicas) {
            if (!politica.esValido(ingresante)) {
                return politica;
            }
        }
        return null;
    }

    // Adaptado para reutilizar la lógica anterior
    public boolean puedeIngresar(Ingresante ingresante) throws Exception {
        return obtenerPoliticaFallida(ingresante) == null;
    }

}
    









































