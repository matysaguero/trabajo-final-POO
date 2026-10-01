package controlador;

import java.util.ArrayList;
import java.util.List;

import modelo.Documento;
import modelo.Ingresante;
import modelo.IngresanteRegular;
import modelo.Pasaporte;
import modelo.PoliticaClan;
import modelo.PoliticaFronteriza;
import modelo.Resolutor;
import modelo.TipoCiudad;
import modelo.TipoClan;
import modelo.TipoIngresante;
import modelo.TipoRaza;

public class Dia {

    public static void main(String[] args) {

        try {

            System.out.println("========== Demo por consola ==========");
            System.out.println("Día 1");
            System.out.println("Regla: el clan JUSTICIALISTA está prohibido.");
            System.out.println();

            // 1. Creamos un documento de prueba
            Pasaporte pasaportePrueba = new Pasaporte(
                "07/09/2026",
                1,
                60,
                170,
                "Argentina",
                "Comodoro Rivadavia",
                false
            );

            // 2. Creamos la lista de documentos
            ArrayList<Documento> documentos = new ArrayList<>();
            documentos.add(pasaportePrueba);

            // 3. Creamos un ingresante de prueba
            Ingresante ingresantePrueba = new IngresanteRegular(
                "Andrea Gonzales",
                170,
                63,
                6.7,
                documentos,
                TipoIngresante.TURISTA,
                TipoRaza.HUMANO,
                TipoClan.LLA,
                TipoCiudad.COMODORO_RIVADAVIA,
                9
            );

            System.out.println("----- DATOS DEL INGRESANTE -----");
            ingresantePrueba.mostrarDetalle();

            // 4. Creamos una política de clan
            PoliticaClan politicaClan = new PoliticaClan(
                List.of(TipoClan.JUSTICIALISTA)
            );

            // 5. Creamos la lista de políticas activas
            List<PoliticaFronteriza> politicas = new ArrayList<>();
            politicas.add(politicaClan);

            // 6. Creamos el resolutor
            Resolutor resolutor = new Resolutor(politicas);

            // 7. Evaluamos al ingresante
            boolean puedeIngresar = resolutor.puedeIngresar(ingresantePrueba);

            System.out.println();
            System.out.println("----- RESULTADO DE LAS POLÍTICAS -----");
            System.out.println("Clan del ingresante: " + ingresantePrueba.getClan());
            System.out.println("Clan prohibido: " + TipoClan.JUSTICIALISTA);
            System.out.println("¿Puede ingresar?: " + puedeIngresar);

            if (puedeIngresar) {
                System.out.println("RESULTADO: El ingresante CUMPLE las políticas.");
            } else {
                System.out.println("RESULTADO: El ingresante NO CUMPLE las políticas.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}