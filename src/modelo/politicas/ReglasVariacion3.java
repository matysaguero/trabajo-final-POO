package modelo.politicas;
import java.time.LocalDate;
import java.util.List;
import modelo.TipoCiudad;
import modelo.TipoClan;
import modelo.TipoRaza;

public class ReglasVariacion3 extends ReglasDia {
    public ReglasVariacion3() {
        super(3, "Variación 3: Fronteras Abiertas", LocalDate.of(2159, 11, 16));
    }
    // Todas las listas vacías, no hay prohibiciones de raza, clan ni origen
    @Override public List<TipoCiudad> getCiudadesProhibidas() { return List.of(); }
    @Override public List<TipoClan> getClanesProhibidos() { return List.of(); }
    @Override public List<TipoRaza> getRazasProhibidas() { return List.of(); }
    @Override public List<String> getReglasDetalladas() {
        return List.of("1. Fronteras Abiertas.", "2. Se admiten todas", "las razas y orígenes.", "3. Revisar documentación.");
    }
}