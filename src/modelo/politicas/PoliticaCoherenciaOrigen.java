package modelo.politicas;

import modelo.Ingresantes.Ingresante;
import modelo.documentos.Documento;

public class PoliticaCoherenciaOrigen extends PoliticaFronteriza {
    
    @Override
    public boolean esValido(Ingresante ingresante) {
        //Obtenemos la ciudad de origen declarada por el ingresante
        String origenDeclarado = ingresante.getCiudadOrigen().name(); 
        
        //Revisamos cada uno de sus documentos
        for (Documento doc : ingresante.getDocumentos()) {
            //Si hay una discrepancia, obviando mayúsculas, retornamos false.
            if (!doc.getOrigen().equalsIgnoreCase(origenDeclarado)) {
                return false; 
            }
        }
        
        return true;
    }
    
    @Override public String getMotivoRechazo() { return "Origen no coincidente entre documento"; }
}