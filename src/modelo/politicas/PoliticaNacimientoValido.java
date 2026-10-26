package modelo.politicas;

import java.time.LocalDate;
import modelo.Ingresantes.Ingresante;
import modelo.documentos.Documento;
import modelo.documentos.DNI;
import modelo.documentos.Pasaporte;

public class PoliticaNacimientoValido extends PoliticaFronteriza {
    private final LocalDate fechaActualDelJuego;

    public PoliticaNacimientoValido(LocalDate fechaActualDelJuego) {
        this.fechaActualDelJuego = fechaActualDelJuego;
    }

    @Override
    public boolean esValido(Ingresante ingresante) {
        for (Documento doc : ingresante.getDocumentos()) {
            if (doc instanceof DNI) {
                if (((DNI) doc).getFechaNacimiento().isAfter(this.fechaActualDelJuego)) {
                    return false;
                }
            }
            if (doc instanceof Pasaporte) {
                if (((Pasaporte) doc).getFechaNacimiento().isAfter(this.fechaActualDelJuego)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String getMotivoRechazo() {
        return "Nacimiento despues de la fecha actual";
    }
}