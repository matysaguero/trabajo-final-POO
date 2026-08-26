import java.util.ArrayList;

public class Ciudadano extends Ingresante{
    private int ramdom; //linea a completar con atributos
    
    public Ciudadano(String nombreDeclarado, int alturaVisual, int pesoEnBalanza, String clan, String ciudadOrigen,Double reputacion, ArrayList<Documento> documentos, int ramdom){
        super(nombreDeclarado,  alturaVisual,  pesoEnBalanza,  clan,  ciudadOrigen,reputacion , documentos);
        this.ramdom = ramdom;
    }

    public void mostrarDetalle(){
        super.mostrarDetalle();
    }
}
