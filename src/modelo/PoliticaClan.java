package modelo;
import java.util.ArrayList;
import java.util.List;

public class PoliticaClan extends PoliticaFronteriza {

private List<TipoClan> clanesProhibidos;

public PoliticaClan(List<TipoClan> clanesProhibidos) {
    // Se valida acá para que el error aparezca al crear la política y no después, dentro de esValido().    
    if (clanesProhibidos == null || clanesProhibidos.isEmpty()) {
        throw new IllegalArgumentException("La lista de clanes prohibidos no puede ser nula ni vacia.");
    }
    this.clanesProhibidos = new ArrayList<>(clanesProhibidos);
}


@Override 
public boolean esValido (Ingresante ingresante){
    
    TipoClan clanIngresante = ingresante.getClan();

    if (clanesProhibidos.contains(clanIngresante)){
        return false;
    }
 return true;
}

}

