package modelo;
import java.util.ArrayList;

public class IngresanteRegular extends Ingresante{
    
    
    public IngresanteRegular(String nombreDeclarado, int alturaVisual, int pesoEnBalanza, double reputacion, ArrayList<Documento> documentos, TipoIngresante tipo, TipoRaza raza, TipoClan clan, TipoCiudad ciudad){
        super(nombreDeclarado, alturaVisual, pesoEnBalanza, reputacion, documentos, tipo, raza, clan, ciudad);
    
    }

@Override 
    public String responderInterrogatorio(){
        return "Vengo de turismo.";
    }
@Override 
    public String darPresentacion(){
        return "Hola, soy un ciudadano regular.";
    }

@Override //NUEVO METODO ABSTRACTO: que devuelve true o false si el ingresante es una amenaza
public boolean esAmenaza() {
    return false;
}

}
