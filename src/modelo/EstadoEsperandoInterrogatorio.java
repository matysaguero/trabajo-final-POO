package modelo;

import modelo.Ingresantes.Ingresante;

public class EstadoEsperandoInterrogatorio  implements  EstadoPartida {

    // El sello requiere completar la entrevista y solicitar los documentos.
    @Override 
    public void usarSello (Partida partida) {
        throw new IllegalStateException("Primero bedes interrogar al ingresante y solicitar sus documentos");
    }

    // Mantiene al ingresante actual en ventanilla.
    @Override
    public void usarMegafono(Partida partida) {
        throw new IllegalStateException("No puedes llamar a otro ingresante mientras ya hay uno en ventanilla.");
    }


    // Todavía no se cumplen los pasos necesarios para aceptar o rechazar.
    @Override 
    public void procesarDecision (Partida partida, boolean descisionJugador){
        throw new IllegalStateException("No puedes decidir antes de interrogar al ingresante, " + "solicitar sus documentos y abrir el sello.");
    }

    // No hay una decisión abierta que se pueda cancelar.
    @Override
    public void cancelarDecision(Partida partida) {
        throw new IllegalStateException("El sello todavía no fue abierto.");
    }

    // Rechaza la solicitud sin alterar el estado.
    @Override 
    public void solicitarDocumentos(Partida partida){
          throw new IllegalStateException(
            "Primero debes interrogar al ingresante para solicitar sus documentos.");
        }

    // Entrevistar es la única acción que hace avanzar desde este estado.
    @Override
    public String interrogar(Partida partida) {
        Ingresante ingresante = partida.getIngresanteActual();

        // Protege la invariante: este estado requiere un ingresante actual.
        if (ingresante == null) {
            throw new IllegalStateException( "No hay ningún ingresante en la ventanilla al que interrogar.");
        }

        // Obtener la respuesta antes de modificar el estado de la partida.
        String respuesta = "- Inspector: ¿A qué se debe su visita?\n" + "- Ingresante: " + ingresante.responderInterrogatorio();

        // La entrevista habilita solicitar documentos, pero aún no abre el sello.
        partida.cambiarEstado(new EstadoEsperandoDocumentos());

        // El modelo notifica el hecho; la vista habilita el botón.
        partida.notificarDocumentosDisponibles();

        return respuesta;
    }










}
