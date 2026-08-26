import java.util.ArrayList;

public class IngresanteRegular extends Ingresante{ // se necesita escribir esto para poder crear un objeto de Ingresante.
    private int ramdom;
    
    public IngresanteRegular(String nombreDeclarado, int alturaVisual, int pesoEnBalanza, String clan, String ciudadOrigen, TipoIngresante tipo, ArrayList<Documento> documentos, int ramdom){
        super(nombreDeclarado,  alturaVisual,  pesoEnBalanza,  clan,  ciudadOrigen, tipo, documentos);
        this.ramdom = ramdom;
    }
    //Se cambia la clase ciudadano por IngresanteRegular y se agrega al constructore el atributo tipo 25/8/26 21:17pm. 

    public void mostrarDetalle(){
        super.mostrarDetalle();
    }
}
