package modelo.politicas;

import java.util.List;

import modelo.TipoCiudad;
import modelo.TipoClan;
import modelo.TipoRaza;

import java.util.List;

public class ReglasDia2 extends ReglasDia {

    public ReglasDia2() {
        super(2, "Día 2: Se endurecen los controles. Se prohíben múltiples ciudades y la raza CYBORG.");
    }

    @Override
    public List<TipoCiudad> getCiudadesProhibidas() {
        return List.of(TipoCiudad.ZOMBAR, TipoCiudad.NAMEK);
    }

    @Override
    public List<TipoClan> getClanesProhibidos() {
        return List.of(TipoClan.ALIEN, TipoClan.UNION_MAGICA);
    }

    @Override
    public List<TipoRaza> getRazasProhibidas() {
        return List.of(TipoRaza.CYBORG);
    }
}