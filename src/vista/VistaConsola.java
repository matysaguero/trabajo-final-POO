package vista;

import modelo.Ingresantes.Ingresante;

import modelo.documentos.Documento;
import modelo.documentos.DNI;
import modelo.documentos.Pasaporte;
import modelo.documentos.Permiso;
import modelo.documentos.Placa;
import modelo.documentos.PapelEnTramite;

public class VistaConsola {

    public void mostrarIngresante(Ingresante ingresante) {

        System.out.println();
        System.out.println("===== NUEVO INGRESANTE =====");

        System.out.println(
            "Nombre: " + ingresante.getNombreDeclarado()
        );

        System.out.println(
            "Altura: " + ingresante.getAlturaVisual()
        );

        System.out.println(
            "Peso: " + ingresante.getPesoEnBalanza()
        );

        System.out.println(
            "Tipo: " + ingresante.getTipoIngresante()
        );

        System.out.println(
            "Raza: " + ingresante.getRaza()
        );

        System.out.println(
            "Clan: " + ingresante.getClan()
        );

        System.out.println(
            "Ciudad: " + ingresante.getCiudadOrigen()
        );

        System.out.println();
        System.out.println("===== DOCUMENTOS =====");

        for (Documento documento : ingresante.getDocumentos()) {
            mostrarDocumento(documento);
        }
    }


    private void mostrarDocumento(Documento documento) {

        System.out.println();
        System.out.println("-------------------------");

        System.out.println(
            "Número ID: " + documento.getNumId()
        );

        System.out.println(
            "Fecha de vencimiento: "
            + documento.getFechaVencimiento()
        );

        System.out.println(
            "Falsificado: " + documento.getTrucho()
        );


        if (documento instanceof Pasaporte pasaporte) {

            System.out.println("Tipo: Pasaporte");

            System.out.println(
                "País: " + pasaporte.getPais()
            );

            System.out.println(
                "Ciudad: " + pasaporte.getCiudad()
            );

            System.out.println(
                "Peso: " + pasaporte.getPeso()
            );

            System.out.println(
                "Altura: " + pasaporte.getAltura()
            );


        } else if (documento instanceof DNI dni) {

            System.out.println("Tipo: DNI");

            System.out.println(
                "Huella: " + dni.getHuella()
            );

            System.out.println(
                "Lugar de nacimiento: "
                + dni.getLugarNacimiento()
            );

            System.out.println(
                "Sexo: " + dni.getSexo()
            );

            System.out.println(
                "Domicilio: " + dni.getDomicilio()
            );


        } else if (documento instanceof Permiso permiso) {

            System.out.println("Tipo: Permiso");

            System.out.println(
                "Sello: " + permiso.getSello()
            );

            System.out.println(
                "Ocupación: " + permiso.getOcupacion()
            );


        } else if (documento instanceof Placa placa) {

            System.out.println("Tipo: Placa");

            System.out.println(
                "Clan: " + placa.getClan()
            );

            System.out.println(
                "ID de placa: " + placa.getIdPlaca()
            );


        } else if (documento instanceof PapelEnTramite papel) {

            System.out.println(
                "Tipo: Papel en trámite"
            );

            System.out.println(
                "Firma legal: " + papel.getFirmaLegal()
            );
        }
    }


    public void mostrarPuedeIngresar(boolean puedeIngresar) {

        System.out.println(
            "¿Puede ingresar según las políticas?: "
            + puedeIngresar
        );
    }


    public void mostrarDecisionJugador(boolean decisionJugador) {

        System.out.println(
            "Decisión del jugador: "
            + (
                decisionJugador
                    ? "ACEPTAR"
                    : "RECHAZAR"
            )
        );
    }


    public void mostrarResultado(
            boolean acierto,
            int reputacion) {

        if (acierto) {

            System.out.println(
                "Decisión correcta."
            );

        } else {

            System.out.println(
                "Decisión incorrecta."
            );
        }

        System.out.println(
            "Reputación actual: " + reputacion
        );
    }


    public void mostrarFinDeJuego() {

        System.out.println(
            "===== FIN DEL JUEGO: reputación agotada ====="
        );
    }
}                              