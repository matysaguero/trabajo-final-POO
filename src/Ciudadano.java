import java.util.ArrayList;

public class Ciudadano extends Ingresante{ // se necesita escribir esto para poder crear un objeto de Ingresante.
    private int ramdom;
    
    public Ciudadano(String nombreDeclarado, int alturaVisual, int pesoEnBalanza, String clan, String ciudadOrigen, ArrayList<Documento> documentos, int ramdom){
        super(nombreDeclarado,  alturaVisual,  pesoEnBalanza,  clan,  ciudadOrigen, documentos);
        this.ramdom = ramdom;
    }

    public void mostrarDetalle(){
        super.mostrarDetalle();
    }
}
