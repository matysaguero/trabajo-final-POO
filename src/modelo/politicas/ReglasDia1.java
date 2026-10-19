package modelo.politicas;

import java.time.LocalDate;
import java.util.List;

import modelo.TipoCiudad;
import modelo.TipoClan;
import modelo.TipoRaza;

public class ReglasDia1 extends ReglasDia {

    public ReglasDia1() {
        super(1, "Día 1: Se prohíbe el ingreso a todo aquél que no sea humano.", LocalDate.of(2159, 11, 14));
    }

    @Override
    public List<TipoCiudad> getCiudadesProhibidas() {
        return List.of();
    }

    @Override
    public List<TipoClan> getClanesProhibidos() {
        return List.of();
    }

    @Override
    public List<TipoRaza> getRazasProhibidas() {
        // Se retornan el resto de razas que no sean humanos.
        return List.of(TipoRaza.ALIEN, TipoRaza.GUARDIANES, TipoRaza.CYBORG);
    }
}