package modelo.politicas;

import java.util.List;
import modelo.Ingresantes.Ingresante;
import modelo.documentos.Documento;

public class PoliticaCoherenciaNombre extends PoliticaFronteriza {
    
    @Override
    public boolean esValido(Ingresante ingresante) {
        List<Documento> documentos = ingresante.getDocumentos();
        
        // Si no tiene documentos o solo tiene uno, no hay discrepancia posible entre papeles
        if (documentos == null || documentos.isEmpty()) {
            return true;
        }
        
        // Tomamos el nombre del primer documento entregado en la mesa como referencia
        String nombreReferencia = documentos.get(0).getNombre();
        
        // Revisamos si algún otro documento tiene un nombre distinto
        for (Documento doc : documentos) {
            if (!doc.getNombre().equalsIgnoreCase(nombreReferencia)) {
                return false; 
            }
        }
        
        return true; // Todos los papeles y documentos presentados tienen el mismo nombre
    }

    @Override 
    public String getMotivoRechazo() { 
        return "Nombres no coincidentes entre documentos"; 
    }
}