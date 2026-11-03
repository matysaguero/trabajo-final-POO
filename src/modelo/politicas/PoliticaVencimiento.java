package modelo.politicas;

import java.time.LocalDate;
//import java.time.format.DateTimeParseException;
import java.util.Objects;

import modelo.Ingresantes.Ingresante;
import modelo.documentos.Documento;

public class PoliticaVencimiento extends PoliticaFronteriza {
    
    private final LocalDate fechaActualDelJuego;

    public PoliticaVencimiento(LocalDate fechaActualDelJuego) {
        if (fechaActualDelJuego == null) {
            throw new IllegalArgumentException("La fecha actual es obligatoria para verificar vencimientos.");
        }
        this.fechaActualDelJuego = Objects.requireNonNull(fechaActualDelJuego);
    }

    @Override
    public boolean esValido(Ingresante ingresante) {
        for (Documento doc : ingresante.getDocumentos()) {
                if (doc.estaVencido(fechaActualDelJuego)) {
                    return false;
                }
            }
            return true;
        }

    @Override public String getMotivoRechazo() { return "Documento Vencido"; }
}