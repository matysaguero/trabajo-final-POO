package modelo.politicas;

import modelo.Ingresantes.Ingresante;
//import modelo.documentos.DNI;
//import modelo.documentos.Documento;
//import modelo.documentos.Pasaporte;

public class PoliticaCoherenciaRaza extends PoliticaFronteriza {
    private String motivoError = "La raza anatómica no coincide con los documentos";

    @Override
    public boolean esValido(Ingresante ingresante) {
        motivoError ="La raza anatómica no coincide con los documentos";
        String razaReal = ingresante.getRaza().name();

        for (String razaDoc :ingresante.obtenerResumenDocumental().razasDeclaradas()) {

            if (!razaDoc.equalsIgnoreCase(razaReal)) {
                if (razaReal.equals("HUMANO") && razaDoc.equalsIgnoreCase("ALIEN")) {

                    motivoError = "No es humano (en realidad si pero el documento dice lo contrario, terrible boludin)";

                } else if (razaReal.equals("ALIEN")  && razaDoc.equalsIgnoreCase("HUMANO")) {
                    motivoError = "No era un humano, ¿de donde habra sacado esos documentos?";

                } else {
                    motivoError = "Falsificación: La raza del ingresante no coincide con la registrada";
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

/* 
Explicación del cambio: se eliminan las referencias concretas a DNI y Pasaporte, 
pero se conservan tus mensajes especiales de rechazo.

Ahora la política pregunta cuáles son las razas declaradas, 
en lugar de preguntar qué tipo de documento contiene cada una.

Además, motivoError se reinicia al comenzar una evaluación, 
evitando arrastrar un mensaje anterior si se reutiliza la misma instancia de la política.
*/