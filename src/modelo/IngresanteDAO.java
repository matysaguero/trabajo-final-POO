package modelo;

import java.util.List;
import modelo.Ingresantes.Ingresante;

public interface IngresanteDAO {
    boolean guardar(Ingresante ingresante);
    List<Ingresante> listarTodos();
}