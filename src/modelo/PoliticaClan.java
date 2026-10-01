package modelo;
import java.util.ArrayList;
import java.util.List;

public class PoliticaClan extends PoliticaFronteriza {

private List<TipoClan> clanesProhibidos;

public PoliticaClan(List<TipoClan> clanesProhibidos) {
    this.clanesProhibidos = clanesProhibidos;
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

