package modelo.politicas;
import java.time.LocalDate;
import java.util.List;
import modelo.TipoCiudad;
import modelo.TipoClan;
import modelo.TipoRaza;

public class ReglasVariacion2 extends ReglasDia {
    public ReglasVariacion2() {
        super(2, "Variación 2: Restricción de Origen", LocalDate.of(2159, 11, 15));
    }
    @Override public List<TipoCiudad> getCiudadesProhibidas() {
        // Si solo se admiten Hasgard y Lander, prohibimos expresamente al resto:
        return List.of(TipoCiudad.BALES, TipoCiudad.ZOMBAR, TipoCiudad.NAMEK);
    }
    @Override public List<TipoClan> getClanesProhibidos() { return List.of(); }
    @Override public List<TipoRaza> getRazasProhibidas() { return List.of(); }
    @Override public List<String> getReglasDetalladas() {
        return List.of("1. Se admiten todas las razas.", "2. UNICAMENTE provenientes", "de HASGARD y LANDER.");
    }
}