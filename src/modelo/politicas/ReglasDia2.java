package modelo.politicas;

import java.time.LocalDate;
import java.util.List;

import modelo.TipoCiudad;
import modelo.TipoClan;
import modelo.TipoRaza;

import java.util.List;

public class ReglasDia2 extends ReglasDia {

    public ReglasDia2() {
        super(2, "Día 2: Se endurecen los controles. Se empiezan a aceptar el resto de razas, unicamente provenientes de HAGARD y LANDER. " +
            "Se prohibe el ingreso a todo aquél que perteneza a la UNIÓN MÁGICA.", LocalDate.of(2159, 11, 15)
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
}