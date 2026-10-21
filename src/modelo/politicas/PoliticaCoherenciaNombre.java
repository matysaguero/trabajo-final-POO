package modelo.politicas;

import modelo.Ingresantes.Ingresante;
import modelo.documentos.Documento;

public class PoliticaCoherenciaNombre extends PoliticaFronteriza {
    
    @Override
    public boolean esValido(Ingresante ingresante) {
        //Obtenemos cómo dice llamarse el ingresante
        String nombreDeclarado = ingresante.getNombreDeclarado();
        
        //Revisamos cada uno de sus documentos
        for (Documento doc : ingresante.getDocumentos()) {
            
            //En cuanto uno de los nombres no coincida, ignorando las mayúsculas por si acaso 
            if (!doc.getNombre().equalsIgnoreCase(nombreDeclarado)) {
                return false; 
            }
        }
        
        return true; //Todos los papeles y documentos presentados tienen el mismo nombre
    }
}