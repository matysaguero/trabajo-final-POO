package modelo.politicas;
 
import java.util.ArrayList;
import java.util.List;
 
import modelo.TipoIngresante;
import modelo.Ingresantes.Ingresante;
 
// Rechaza a los ingresantes de un tipo prohibido (ej.:CIUDADANO,TURISTA,TRABAJADOR,ALIADO).
// No confundir con PoliticaIngresante, que rechaza a los que son amenaza.
public class PoliticaTipoIngresante extends PoliticaFronteriza {
 
    private final List<TipoIngresante> tiposProhibidos;
 
    public PoliticaTipoIngresante(List<TipoIngresante> tiposProhibidos) {
        if (tiposProhibidos == null || tiposProhibidos.isEmpty()) {
            throw new IllegalArgumentException("La lista de tipos prohibidos no puede ser nula ni vacia.");
        }
        this.tiposProhibidos = new ArrayList<>(tiposProhibidos);
    }
 
    @Override
    public boolean esValido(Ingresante ingresante) {
        return !tiposProhibidos.contains(ingresante.getTipoIngresante());
    }
}
