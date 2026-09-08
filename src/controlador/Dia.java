package controlador;

import java.util.ArrayList;
import modelo.Documento;
import modelo.Ingresante;
import modelo.IngresanteRegular;
import modelo.Pasaporte;
import modelo.TipoCiudad;
import modelo.TipoClan;
import modelo.TipoIngresante;
import modelo.TipoRaza;

public class Dia {
    public static void main(String[] args) {
        System.out.println("========== Demo por consola: ==========");
        System.out.println("Dia 1");
        
        Pasaporte pasaportePrueba = new Pasaporte("07/09/2026", 1, 60, 170, "Argentina", "Comodoro Rivadavia", false);
        ArrayList<Documento> documentos = new ArrayList<>();
        documentos.add(pasaportePrueba); // en teoria aca tengo una lista de Documentos (clase padre), con un solo elemento que es pasaportePrueba (clase hija Pasaporte).

        //clase Ciudadano a construir todavia - 23/8/26 00:47am.
        Ingresante ingresantePrueba = new IngresanteRegular("Andrea Gonzales", 170, 63, "LLA", "Comodoro Rivadavia", 6.7, documentos, TipoIngresante.TURISTA, 9);
        ingresantePrueba.mostrarDetalle();
    
        PoliticaFronterizaDia1 pruebaPoliticaDia1 = new PoliticaFronterizaDia1("07/09/2026", TipoCiudad.COMODORO_RIVADAVIA, TipoClan.JUSTICIALISTA, TipoRaza.HUMANO);
        pruebaPoliticaDia1.mostrarDetalle();

        
    }
}