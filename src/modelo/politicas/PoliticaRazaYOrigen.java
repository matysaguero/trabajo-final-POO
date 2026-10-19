package modelo.politicas;

import java.util.List;
import modelo.TipoRaza;
import modelo.TipoCiudad;
import modelo.Ingresantes.Ingresante;

public class PoliticaRazaYOrigen extends PoliticaFronteriza {
    private final List<TipoCiudad> ciudadesPermitidas;

    public PoliticaRazaYOrigen(List<TipoCiudad> ciudadesPermitidas) {
        if (ciudadesPermitidas == null || ciudadesPermitidas.isEmpty()) {
            throw new IllegalArgumentException("La lista de ciudades permitidas no puede ser nula ni vacía.");
        }
        this.ciudadesPermitidas = ciudadesPermitidas;
    }

    @Override
    public boolean esValido(Ingresante ingresante) {
        // Si es HUMANO, pasa siempre sin importar el origen
        if (ingresante.getRaza() == TipoRaza.HUMANO) {
            return true;
        } else {
        
        //Si NO es humano, solo es válido si su ciudad de origen está en la lista permitida (HASGARD o LAMBER)
            return this.ciudadesPermitidas.contains(ingresante.getCiudadOrigen());
        }
    }
}