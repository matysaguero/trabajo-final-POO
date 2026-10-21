package modelo.politicas;
 
import java.util.ArrayList;
import java.util.List;
 
import modelo.TipoRaza;
import modelo.Ingresantes.Ingresante;
 
// Rechaza a los ingresantes cuya raza esté prohibida.
public class PoliticaProhibicionRaza extends PoliticaFronteriza {
 
    private final List<TipoRaza> razasProhibidas;
 
    public PoliticaProhibicionRaza(List<TipoRaza> razasProhibidas) {
        if (razasProhibidas == null || razasProhibidas.isEmpty()) {
            throw new IllegalArgumentException("La lista de razas prohibidas no puede ser nula ni vacia.");
        }
        this.razasProhibidas = new ArrayList<>(razasProhibidas);
    }
 
    @Override
    public boolean esValido(Ingresante ingresante) {
        return !razasProhibidas.contains(ingresante.getRaza());
    }
}
