package modelo.politicas;

import modelo.Ingresantes.Ingresante;
import modelo.documentos.Documento;
import modelo.documentos.DNI;
import modelo.documentos.Permiso;

public class PoliticaCoherenciaDni extends PoliticaFronteriza {

    @Override
    public boolean esValido(Ingresante ingresante) {
        String dniReferencia = null;
        
        for (Documento doc : ingresante.getDocumentos()) {
            if (doc instanceof DNI) {
                dniReferencia = String.valueOf(((DNI) doc).getNroDni());
            }
        }
        
        if (dniReferencia == null) return true; // Si falta DNI, lo rechaza otra política
        
        for (Documento doc : ingresante.getDocumentos()) {
            if (doc instanceof Permiso) {
                if (!((Permiso) doc).getDni().equals(dniReferencia)) {
                    return false; // El DNI del permiso no coincide con la tarjeta DNI
                }
            }
        }
        return true;
    }

    @Override
    public String getMotivoRechazo() {
        return "DNIs no coincidente entre documentos";
    }
}
