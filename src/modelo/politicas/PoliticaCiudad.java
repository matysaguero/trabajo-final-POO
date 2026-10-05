package modelo.politicas;
 
import java.util.ArrayList;
import java.util.List;
 
import modelo.TipoCiudad;
import modelo.Ingresantes.Ingresante;

//Rechaza a los ingresantges que vengan de una ciudad prohibida.
public class PoliticaCiudad extends PoliticaFronteriza {
    
    private final List<TipoCiudad> ciudadesProhibidas;

    public PoliticaCiudad(List<TipoCiudad> ciudadesProhibidas) {
        if (ciudadesProhibidas == null || ciudadesProhibidas.isEmpty()) {
            throw new IllegalArgumentException("La lista de ciudades prohibidas no puede ser nula ni vacia.");
        }
        this.ciudadesProhibidas = new ArrayList<>(ciudadesProhibidas);
    }

    @Override
    public boolean esValido(Ingresante ingresante) {
        return !ciudadesProhibidas.contains(ingresante.getCiudadOrigen());
    }

}
