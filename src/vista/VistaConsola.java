
package vista;

import modelo.Ingresante;

public class VistaConsola {

    public void mostrarIngresante(Ingresante ingresante) {
        System.out.println("===== NUEVO INGRESANTE =====");
        ingresante.mostrarDetalle();
        System.out.println();
    }

    public void mostrarPuedeIngresar(boolean puedeIngresar) {
        System.out.println("¿Puede ingresar según las políticas?: " + puedeIngresar);
    }

    public void mostrarDecisionJugador(boolean decisionJugador) {

        if (decisionJugador) {
            System.out.println("Decisión del inspector: ACEPTAR");
        } else {
            System.out.println("Decisión del inspector: RECHAZAR");
        }
    }

    public void mostrarResultado(boolean acierto, int reputacion) {

        if (acierto) {
            System.out.println("Resultado: decisión correcta.");
        } else {
            System.out.println("Resultado: decisión incorrecta.");
        }

        System.out.println("Reputación actual: " + reputacion);
        System.out.println();
    }                       

    public void mostrarFinDeJuego() {   // <-- método nuevo
        System.out.println("===== FIN DEL JUEGO: reputación agotada =====");
    }

}                              