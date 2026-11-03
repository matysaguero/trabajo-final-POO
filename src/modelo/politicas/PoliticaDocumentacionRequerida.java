package modelo.politicas;

import modelo.Ingresantes.Ingresante;
import modelo.TipoIngresante;
//import modelo.documentos.DNI;
//import modelo.documentos.Pasaporte;
//import modelo.documentos.Permiso;
import modelo.documentos.RequisitoDocumental;
import modelo.documentos.ResumenDocumental;

public class PoliticaDocumentacionRequerida extends PoliticaFronteriza {
    
   @Override
    public boolean esValido(Ingresante ingresante) {
        
        ResumenDocumental resumen = ingresante.obtenerResumenDocumental();

        // Todos necesitan DNI.
        if (!resumen.acredita(RequisitoDocumental.DNI)) {
            return false;
        }

        TipoIngresante tipo =
            ingresante.getTipoIngresante();

        // Los no ciudadanos necesitan pasaporte.
        if (tipo != TipoIngresante.CIUDADANO && !resumen.acredita(RequisitoDocumental.PASAPORTE)) {

            return false;
        }

        // Los trabajadores necesitan permiso.
        if (tipo == TipoIngresante.TRABAJADOR && !resumen.acredita(RequisitoDocumental.PERMISO)) {
            return false;
        }

        return true;
    }

    @Override public String getMotivoRechazo() { return "Falta documentacion requerida"; }
}

/* 
Explicación del cambio: reemplazamos la identificación de clases Java 
por la comprobación de requisitos documentales.

Los if que quedan expresan reglas del negocio, 
por lo que no hace falta eliminarlos.

*/