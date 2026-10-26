package modelo.politicas;

import modelo.Ingresantes.Ingresante;
import modelo.documentos.DNI;
import modelo.documentos.Documento;
import modelo.documentos.Pasaporte;

public class PoliticaCoherenciaRaza extends PoliticaFronteriza {
    private String motivoError = "La raza anatómica no coincide con los documentos";

    @Override
    public boolean esValido(Ingresante ingresante) {
        String razaReal = ingresante.getRaza().name();

        for (Documento doc : ingresante.getDocumentos()) {
            String razaDoc = null;

            // Extraemos la raza según el tipo de documento usando polimorfismo/instanceof
            if (doc instanceof DNI) {
                razaDoc = ((DNI) doc).getRaza();
            } else if (doc instanceof Pasaporte) {
                razaDoc = ((Pasaporte) doc).getRaza();
            }

            // Si el documento tiene raza y no coincide con la real del ingresante
            if (razaDoc != null && !razaDoc.equalsIgnoreCase(razaReal)) {
                
                // EASTER EGG: Si es un humano disfrazado de alien con papeles de alien (Caso Xylar)
                if (razaReal.equals("HUMANO") && razaDoc.equalsIgnoreCase("ALIEN")) {
                    this.motivoError = "No es humano (en realidad si pero el documento dice lo contrario, terrible boludin)";
                } else if(razaReal.equals("ALIEN") && razaDoc.equalsIgnoreCase("HUMANO")) {
                    this.motivoError = "No era un humano, ¿de donde habra sacado esos documentos?";}
                else {
                    this.motivoError = "Falsificación: La raza del ingresante no coincide con la registrada";
                }
                return false;
            }
        }
        return true;
    }

    @Override
    public String getMotivoRechazo() {
        return this.motivoError;
    }
}