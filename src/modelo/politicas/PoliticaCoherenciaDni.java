package modelo.politicas;

import java.util.List;

import modelo.Ingresantes.Ingresante;
//import modelo.documentos.Documento;
//import modelo.documentos.DNI;
//import modelo.documentos.Permiso;
import modelo.documentos.ResumenDocumental;


public class PoliticaCoherenciaDni extends PoliticaFronteriza {

    @Override
    public boolean esValido(Ingresante ingresante) {
        ResumenDocumental resumen = ingresante.obtenerResumenDocumental();
        List<String> numerosDni = resumen.numerosDni();

        if (numerosDni.isEmpty()) {
            return true;
        }

        String referencia = numerosDni.get(numerosDni.size() - 1);

        for (String dniEnPermiso : resumen.dniEnPermisos()) {
            if (!referencia.equals(dniEnPermiso)) {
                return false;
            }
        }
        return true;
    }
    /* 
    Explicación del cambio: eliminamos los imports de DNI, Permiso y Documento. La política ahora compara cadenas de texto recibidas del resumen.
    
    La ausencia de DNI sigue siendo responsabilidad de PoliticaDocumentacionRequerida, 
    por eso este método devuelve true si no hay ninguno.
    
    También conservamos el comportamiento anterior de elegir 
    el último DNI si se presentan varios.
    */

    @Override
    public String getMotivoRechazo() {
        return "DNIs no coincidente entre documentos";
    }
}
