package modelo.politicas;
 
import java.util.ArrayList;
import java.util.List;
 
import modelo.TipoCiudad;
import modelo.Ingresantes.Ingresante;

//Rechaza a los ingresantges que vengan de una ciudad prohibida.
public class PoliticaProhibicionCiudad extends PoliticaFronteriza {
    
    private final List<TipoCiudad> ciudadesProhibidas;

    public PoliticaProhibicionCiudad(List<TipoCiudad> ciudadesProhibidas) {
        if (ciudadesProhibidas == null) {
            throw new IllegalArgumentException("La lista de ciudades prohibidas no puede ser nula.");
        }
        this.ciudadesProhibidas = new ArrayList<>(ciudadesProhibidas);
    }

    @Override
    public boolean esValido(Ingresante ingresante) {
        return !ciudadesProhibidas.contains(ingresante.getCiudadOrigen());
    }

    @Override public String getMotivoRechazo() { return "Ciudad de origen prohibida"; }
}
