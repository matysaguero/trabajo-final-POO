package modelo.audio;

/* Pantallas por las que puede pasar el jugador.
 * Cada constante sabe si en esa pantalla corresponde que suene la música de fondo.
 * Esta es la ÚNICA regla de negocio sobre "dónde suena": para cambiarla alcanza
 * con modificar el booleano de la constante, sin tocar vistas ni controladores.*/
public enum PantallaJuego {

    MENU_PRINCIPAL(true),
    TUTORIAL(true),
    JUEGO(true),
    FIN_PARTIDA(false); 

    private final boolean conMusica;

    PantallaJuego(boolean conMusica) {
        this.conMusica = conMusica;
    }

    public boolean tieneMusica() {
        return this.conMusica;
    }
}