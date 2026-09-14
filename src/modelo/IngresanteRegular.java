package modelo;
import java.util.ArrayList;

public class IngresanteRegular extends Ingresante{ // se necesita escribir esto para poder crear un objeto de Ingresante.
    private final int ramdom;
    
    public IngresanteRegular(String nombreDeclarado, int alturaVisual, int pesoEnBalanza, double reputacion, ArrayList<Documento> documentos, TipoIngresante tipo, TipoClan clan, TipoRaza raza, TipoCiudad ciudadOrigen, int ramdom){
        super(nombreDeclarado,  alturaVisual,  pesoEnBalanza,  reputacion, documentos, tipo, raza, clan, ciudadOrigen);
        this.ramdom = ramdom;
    }
    //Se cambia la clase ciudadano por IngresanteRegular y se agrega al constructore el atributo tipo 25/8/26 21:17pm. 


public int getRamdom(){
    return this.ramdom;
}

@Override 
    public void mostrarDetalle(){
        super.mostrarDetalle();
    }

@Override 
    public String responderInterrogatorio(){
        return "Vengo de turismo.";
    }

}
