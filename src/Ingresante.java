import java.util.ArrayList;

public abstract class Ingresante {
    protected String nombreDeclarado;
    protected int alturaVisual;
    protected int pesoEnBalanza;
    protected String clan;
    protected String ciudadOrigen;
    protected ArrayList<Documento> documentos;    

    public Ingresante(String nombreDeclarado, int alturaVisual, int pesoEnBalanza, String clan, String ciudadOrigen, ArrayList<Documento> documentos){
        if (nombreDeclarado == null || nombreDeclarado.isEmpty()){
            throw new IllegalArgumentException("el nombre declarado no puede ser nulo ni vacio");
        }
        if (alturaVisual <= 0){
            throw new IllegalArgumentException("la altura declarada no puede ser cero ni negativa");
        }
        if (pesoEnBalanza <= 0){
            throw new IllegalArgumentException("el peso no puede ser cero ni negativo");
        }
        if (clan == null || clan.isEmpty()){
            throw new IllegalArgumentException("el clan no puede ser nulo ni vacio");
        }
        if (ciudadOrigen == null || ciudadOrigen.isEmpty()){
            throw new IllegalArgumentException("ciudadOrigen no puede ser nulo ni vacio");
        }
        this.nombreDeclarado = nombreDeclarado;
        this.alturaVisual = alturaVisual;
        this.pesoEnBalanza = pesoEnBalanza;
        this.clan = clan;
        this.ciudadOrigen = ciudadOrigen;
        this.documentos = documentos;
    }
    
    public String getNombreDeclarado(){
        return this.nombreDeclarado;
    }

    public int getAlturaVisual(){
        return this.alturaVisual;
    }

    public int getPesoEnBalanza(){
        return this.pesoEnBalanza;
    }

    public String getClan(){
        return this.clan;
    }

    public String getCiudadOrigen(){
        return this.ciudadOrigen;
    }

    public void mostrarDetalle(){
        System.out.println("Mi nombre es: "+ nombreDeclarado);
        System.out.println("Mi altura es: "+ alturaVisual+"cm");
        System.out.println("El peso muestra: "+ pesoEnBalanza+"kg");
        System.out.println("Soy de: "+ clan +", y vengo de:" + ciudadOrigen);
        
        for (int i = 0; i < documentos.size(); i++) {
            Documento documento = documentos.get(i);
            documento.mostrarDetalle();
        }

    }
    
 // Métodos abstractos para la interacción, comentados porque dan problemas, necesito instanciarlos en clases hijas y no estan desarrollados todavia.
/*public abstract String darPresentacion();
public abstract String responderInterrogatorio();*/

}

