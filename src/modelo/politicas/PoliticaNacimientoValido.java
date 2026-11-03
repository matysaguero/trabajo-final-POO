package modelo.politicas;

import java.time.LocalDate;
import java.util.Objects;

import modelo.Ingresantes.Ingresante;
//import modelo.documentos.Documento;
//import modelo.documentos.DNI;
//import modelo.documentos.Pasaporte;

public class PoliticaNacimientoValido extends PoliticaFronteriza {
    private final LocalDate fechaActualDelJuego;

    public PoliticaNacimientoValido(LocalDate fechaActualDelJuego) {
        this.fechaActualDelJuego =  Objects.requireNonNull(fechaActualDelJuego);
    }

    @Override
    public boolean esValido(Ingresante ingresante) {

        for (LocalDate nacimiento : ingresante.obtenerResumenDocumental().nacimientosDeclarados()) {
            if (nacimiento.isAfter(fechaActualDelJuego)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String getMotivoRechazo() {
        return "Nacimiento despues de la fecha actual";
    }
}

/*
Explicación del cambio: la política ya no sabe qué documentos tienen fecha de nacimiento. 
Cada documento decide si aporta ese dato y el resumen se encarga de reunirlo.
*/