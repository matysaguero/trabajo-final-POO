package modelo;

public class EstadoAtendiendoIngresante implements EstadoPartida{
    
    // El jugador presiona el sello.
    @Override
    public void usarSello(Partida partida){
        // Hay un ingresante, por lo tanto puede abrir el sello.
        partida.cambiarEstado(new EstadoSelloAbierto());

    }

    @Override
    public void usarMegafono(Partida partida) {
        System.out.println("No puede llamar a otro ingresante mientras ya hay uno en ventanilla.");
    }


    @Override
    public void procesarDecision(Partida partida, boolean decisionJugador) {
        System.out.println("Primero debe abrir el sello para decidir.");
    }

}
