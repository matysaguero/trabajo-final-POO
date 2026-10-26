package modelo.politicas;

import java.util.List;
import modelo.Ingresantes.Ingresante;
import modelo.documentos.Documento;

public class PoliticaCoherenciaOrigen extends PoliticaFronteriza {
    
    @Override
    public boolean esValido(Ingresante ingresante) {
        List<Documento> documentos = ingresante.getDocumentos();
        
        // Si no tiene documentos o solo tiene uno, no hay discrepancia posible entre papeles
        if (documentos == null || documentos.isEmpty()) {
            return true;
        }
        
        // Tomamos el origen del primer documento entregado en la mesa como referencia
        String origenReferencia = documentos.get(0).getOrigen();
        
        // Revisamos si algún otro documento tiene un origen distinto
        for (Documento doc : documentos) {
            if (!doc.getOrigen().equalsIgnoreCase(origenReferencia)) {
                return false; 
            }
        }
        
        return true; // Todos los papeles presentados declaran el mismo origen
    }
    
    @Override 
    public String getMotivoRechazo() { 
        return "Origen no coincidente entre documentos"; 
    }
}