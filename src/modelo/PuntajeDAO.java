package modelo;

import java.util.List;

public interface PuntajeDAO {
    boolean guardar(Puntaje puntaje);
    List<Puntaje> mejores(int cantidad);
}