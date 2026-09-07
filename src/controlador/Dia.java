package controlador;

import java.util.ArrayList;
import modelo.Documento;
import modelo.Pasaporte;

public class Dia {
    public static void main(String[] args) {
        System.out.println("========== Demo por consola: ==========");
        System.out.println("Dia 1");
        
        Pasaporte pasaportePrueba = new Pasaporte("07/30", "AFA", "Mujer Rubia", 3, 60, 170, "Argentina", "Comodoro Rivadavia", false);
        ArrayList<Documento> documentos = new ArrayList<>();
        documentos.add(pasaportePrueba); // en teoria aca tengo una lista de Documentos (clase padre), con un solo elemento que es pasaportePrueba (clase hija Pasaporte).

    }
}