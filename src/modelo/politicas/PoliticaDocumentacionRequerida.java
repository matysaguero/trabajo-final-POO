package modelo.politicas;

import modelo.Ingresantes.Ingresante;
import modelo.TipoIngresante;
import modelo.documentos.DNI;
import modelo.documentos.Pasaporte;
import modelo.documentos.Permiso;

public class PoliticaDocumentacionRequerida extends PoliticaFronteriza {
    
    @Override
    public boolean esValido(Ingresante ingresante) {
        boolean tieneDNI = false;
        boolean tienePasaporte = false;
        boolean tienePermiso = false;
        
        // Verificamos qué papeles trajo a la mesa
        for (var doc : ingresante.getDocumentos()) {
            if (doc instanceof DNI) tieneDNI = true;
            if (doc instanceof Pasaporte) tienePasaporte = true;
            if (doc instanceof Permiso) tienePermiso = true;
        }
        
        // Regla base: TODOS necesitan DNI
        if (!tieneDNI) return false;
        
        // Regla extranjeros: Si NO es ciudadano de tu imperio, necesita Pasaporte
        if (ingresante.getTipoIngresante() != TipoIngresante.CIUDADANO && !tienePasaporte) {
            return false;
        }
        
        // Regla trabajadores: Si ES trabajador, necesita su permiso de trabajo.
        if (ingresante.getTipoIngresante() == TipoIngresante.TRABAJADOR && !tienePermiso) { 
            return false;
        }
        return true;
    }
}