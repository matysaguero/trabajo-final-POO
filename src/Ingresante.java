import java.util.ArrayList;

public abstract class Ingresante {
    protected String nombre_declarado;
    protected int altura_visual;
    protected int peso_en_balanza;
    protected String clan;
    protected String ciudad_origen;
    protected ArrayList<Documento> documentos;    

    public Ingresante(String nombre_declarado, int altura_visual, int peso_en_balanza, String clan, String ciudad_origen, ArrayList<Documento> documentos){
        if (nombre_declarado == null || nombre_declarado.isEmpty()){
            throw new IllegalArgumentException("el nombre declarado no puede ser nulo ni vacio");
        }
        if (altura_visual <= 0){
            throw new IllegalArgumentException("la altura declarada no puede ser cero ni negativa");
        }
        if (peso_en_balanza <= 0){
            throw new IllegalArgumentException("el peso no puede ser cero ni negativo");
        }
        if (clan == null || clan.isEmpty()){
            throw new IllegalArgumentException("el clan no puede ser nulo ni vacio");
        }
        if (ciudad_origen == null || ciudad_origen.isEmpty()){
            throw new IllegalArgumentException("ciudad_origen no puede ser nulo ni vacio");
        }
        this.nombre_declarado = nombre_declarado;
        this.altura_visual = altura_visual;
        this.peso_en_balanza = peso_en_balanza;
        this.clan = clan;
        this.ciudad_origen = ciudad_origen;
        this.documentos = documentos;
    }
    
    public String getNombre_declarado(){
        return this.nombre_declarado;
    }

    public int getAltura_visual(){
        return this.altura_visual;
    }

    public int getPeso_en_balanza(){
        return this.peso_en_balanza;
    }

    public String getClan(){
        return this.clan;
    }

    public String getCiudad_Origen(){
        return this.ciudad_origen;
    }

 // Métodos abstractos para la interacción
    public abstract String darPresentacion();
    public abstract String responderInterrogatorio();

}

