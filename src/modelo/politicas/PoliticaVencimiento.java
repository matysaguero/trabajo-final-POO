package modelo.politicas;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import modelo.Ingresantes.Ingresante;
import modelo.documentos.Documento;

public class PoliticaVencimiento extends PoliticaFronteriza {
    
    private final LocalDate fechaActualDelJuego;

    public PoliticaVencimiento(LocalDate fechaActualDelJuego) {
        if (fechaActualDelJuego == null) {
            throw new IllegalArgumentException("La fecha actual es obligatoria para verificar vencimientos.");
        }
        this.fechaActualDelJuego = fechaActualDelJuego;
    }

    @Override
    public boolean esValido(Ingresante ingresante) {
        for (Documento doc : ingresante.getDocumentos()) {
            try {
                // Gracias al polimorfismo, no nos importa si es DNI, Pasaporte o Permiso.
                // Simplemente le pedimos la fecha.
                LocalDate vencimientoDoc = (doc.getFechaVencimiento());
                
                // Si la fecha de vencimiento es ANTERIOR a la fecha de hoy, está vencido -> Rechazar.
                if (vencimientoDoc.isBefore(this.fechaActualDelJuego)) {
                    return false; // El ingresante tiene al menos un documento vencido
                }
            } catch (DateTimeParseException e) {
                // Si la fecha tiene un formato incorrecto o es falsa/ilegible, rechazamos.
                return false;
            }
        }
        return true; // Todos los documentos están vigentes
    }
}