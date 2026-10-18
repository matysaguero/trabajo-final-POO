package modelo;

import java.util.ArrayList;
import java.util.List;

public class PuntajeDAOMemoria implements PuntajeDAO {
    private final List<Puntaje> puntajes = new ArrayList<>();

    @Override
    public boolean guardar(Puntaje puntaje) {
        if (puntaje == null) {
            return false;
        }
        this.puntajes.add(puntaje);
        return true;
    }

    @Override
    public List<Puntaje> mejores(int cantidad) {
        List<Puntaje> ordenados = new ArrayList<>(this.puntajes);
        ordenados.sort((a, b) -> b.getReputacion() - a.getReputacion());
        return ordenados.subList(0, Math.min(cantidad, ordenados.size()));
    }
}