package modelo.politicas;

import java.time.LocalDate;
import java.util.List;

import modelo.TipoCiudad;
import modelo.TipoClan;
import modelo.TipoRaza;

import java.util.List;

public class ReglasDia2 extends ReglasDia {

    public ReglasDia2() {
        super(2, "Día 2: Se endurecen los controles.", LocalDate.of(2159, 11, 15)
        );
    }

    @Override
    public List<TipoCiudad> getCiudadesProhibidas() {
        return List.of();
    }

    @Override
    public List<TipoClan> getClanesProhibidos() {
        return List.of(TipoClan.UNION_MAGICA);
    }

    @Override
    public List<TipoRaza> getRazasProhibidas() {
        return List.of();
    }

    @Override
    public List<String> getReglasDetalladas() {
        return List.of("Ahora pueden pasar el resto de razas, UNICAMENTE provenientes de HASGARD y LANDER", "PROHIBIDO el acceso todo aquél que pertenezca a la UNION MÁGICA.");
    }
}