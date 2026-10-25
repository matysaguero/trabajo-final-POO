package modelo;

public class EstadoEsperandoLlamado implements EstadoPartida {

    @Override 
    public void usarSello (Partida partida) {
        throw new IllegalStateException("No hay ningún ingresante en la ventanilla. " + "Primero debes llamar a uno con el megáfono.");
    }

    //llamar a un ingresante no significa que ya tengas sus documentos disponibles para inspección AUN.
    @Override
    public void usarMegafono(Partida partida) {
        boolean haySiguiente = partida.prepararSiguienteIngresante();
        if (haySiguiente) {
            partida.cambiarEstado(new EstadoEsperandoInterrogatorio());
        } else {
            partida.cambiarEstado(new EstadoPartidaFinalizada());
        }
    }


    @Override 
    public void procesarDecision (Partida partida, boolean descisionJugador){
        throw new IllegalStateException( "No puedes aceptar o rechazar sin un ingresante en la ventanilla.");
    }

    @Override
    public void cancelarDecision(Partida partida) {
        throw new IllegalStateException("No tomar una decisión si no has abierto el sello.");
    }

    @Override 
    public void solicitarDocumentos(Partida partida){
        throw new IllegalStateException ("No pude abirir los documentos que no fueron solicitados.");
    }

    @Override
    public String interrogar(Partida partida) {
        throw new IllegalStateException("No hay ningún ingresante en la ventanilla al que interrogar.");
    }
}