package modelo.politicas;

import java.util.List;

import modelo.TipoCiudad;
import modelo.TipoClan;
import modelo.TipoRaza;

public class ReglasDia1 extends ReglasDia {

    public ReglasDia1() {
        super(1, "Día 1: Se prohíbe el ingreso a miembros del clan ALIEN y de la ciudad ZOMBAR.");
    }

    @Override
    public List<TipoCiudad> getCiudadesProhibidas() {
        return List.of(TipoCiudad.ZOMBAR);
    }

    @Override
    public List<TipoClan> getClanesProhibidos() {
        return List.of(TipoClan.ALIEN);
    }

    @Override
    public List<TipoRaza> getRazasProhibidas() {
        // En el día 1 no hay razas prohibidas
        return List.of();
    }
}