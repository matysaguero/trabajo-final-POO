package modelo;
import java.util.ArrayList;

public class IngresanteRegular extends Ingresante{
    private int ramdom;
    
    public IngresanteRegular(String nombreDeclarado, int alturaVisual, int pesoEnBalanza, double reputacion, ArrayList<Documento> documentos, TipoIngresante tipo, TipoRaza raza, TipoClan clan, TipoCiudad ciudad, int ramdom){
        super(nombreDeclarado, alturaVisual, pesoEnBalanza, reputacion, documentos, tipo, raza, clan, ciudad);
        this.ramdom = ramdom;
    }

    public void mostrarDetalle(){
        super.mostrarDetalle();
    }

@Override 
    public String responderInterrogatorio(){
        return "Vengo de turismo.";
    }

}
