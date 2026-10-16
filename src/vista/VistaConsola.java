package vista;

import modelo.Ingresantes.Ingresante;
import modelo.documentos.Documento;

public class VistaConsola {

    public void mostrarIngresante(Ingresante ingresante) {
        System.out.println();
        System.out.println("===== NUEVO INGRESANTE =====");
        System.out.println("Nombre: " + ingresante.getNombreDeclarado());
        System.out.println("Altura: " + ingresante.getAlturaVisual());
        System.out.println("Peso: " + ingresante.getPesoEnBalanza());
        System.out.println("Tipo: " + ingresante.getTipoIngresante());
        System.out.println("Raza: " + ingresante.getRaza());
        System.out.println("Clan: " + ingresante.getClan());
        System.out.println("Ciudad: " + ingresante.getCiudadOrigen());
        System.out.println();
        System.out.println("===== DOCUMENTOS =====");

        for (Documento documento : ingresante.getDocumentos()) {
            mostrarDocumento(documento);
        }
    }

    private void mostrarDocumento(Documento documento) {
        System.out.println();
        System.out.println("-------------------------");
        System.out.println("Tipo: " + documento.getClass().getSimpleName());
        System.out.println("Titular: " + documento.getNombre());
        System.out.println("Origen: " + documento.getOrigen());
        System.out.println("Válido: " + documento.isValido());
        System.out.println(documento.obtenerDetalles());
    }

    public void mostrarPuedeIngresar(boolean puedeIngresar) {
        System.out.println("¿Puede ingresar según las políticas?: " + puedeIngresar);
    }

    public void mostrarDecisionJugador(boolean decisionJugador) {
        System.out.println(
            "Decisión del jugador: " + (decisionJugador ? "ACEPTAR" : "RECHAZAR")
        );
    }

    public void mostrarResultado(boolean acierto, int reputacion) {
        if (acierto) {
            System.out.println("Decisión correcta.");
        } else {
            System.out.println("Decisión incorrecta.");
        }
        System.out.println("Reputación actual: " + reputacion);
    }

    public void mostrarFinDeJuego() {
        System.out.println("===== FIN DEL JUEGO: reputación agotada =====");
    }
}