package modelo;

public class EstadoEsperandoLlamado implements EstadoPartida {

    @Override 
    public void usarSello (Partida partida) {
        throw new IllegalStateException("No hay ningún ingresante en la ventanilla. " + "Primero debes llamar a uno con el megáfono.");
    }

    @Override 
    public void usarMegafono (Partida partida) {
        // Obtener el siguiente ingresante de la fila.
        boolean haySiguiente = partida.prepararSiguienteIngresante();

        if (haySiguiente) {
            // LLama el ingresante y aparece en ventanilla, cambiando de estado: EstadoEsperandoLlamado
                partida.cambiarEstado(new EstadoAtendiendoIngresante());
        } else {
            // No quedan mas ingresantes en la fila.
            partida.cambiarEstado(new EstadoPartidaFinalizada());
        }
    }

    @Override 
    public void procesarDecision (Partida partida, boolean descisionJugador){
        throw new IllegalStateException( "No puedes aceptar o rechazar sin un ingresante en la ventanilla.");
    }











}