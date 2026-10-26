package modelo.politicas;
import java.time.LocalDate;
import java.util.List;
import modelo.TipoCiudad;
import modelo.TipoClan;
import modelo.TipoRaza;

public class ReglasVariacion1 extends ReglasDia {
    public ReglasVariacion1() {
        super(1, "Variación 1: Solo Humanos", LocalDate.of(2159, 11, 14));
    }
    @Override public List<TipoCiudad> getCiudadesProhibidas() { return List.of(); }
    @Override public List<TipoClan> getClanesProhibidos() { return List.of(); }
    @Override public List<TipoRaza> getRazasProhibidas() {
        return List.of(TipoRaza.ALIEN, TipoRaza.GUARDIANES, TipoRaza.CYBORG);
    }
    @Override public List<String> getReglasDetalladas() {
        return List.of("1. SOLO pasan humanos!", "2. PROHIBIDO el ingreso", "a cualquier otra Raza.");
    }
}