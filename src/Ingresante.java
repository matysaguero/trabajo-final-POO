import java.util.ArrayList;

public class Ingresante {
    protected String nombre_declarado;
    protected int altura_visual;
    protected int peso_en_balanza;
    protected String clan;
    protected String ciudad_origen;
    public ArrayList<Ingresante> ingresantes;    

    public Ingresante(String nombre_declarado, int altura_visual, int peso_en_balanza, String clan, String ciudad_origen, ArrayList<Ingresante> ingresantes){
        this.nombre_declarado = nombre_declarado;
        this.altura_visual = altura_visual;
        this.peso_en_balanza = peso_en_balanza;
        this.clan = clan;
        this.ciudad_origen = ciudad_origen;
        this.ingresantes = ingresantes;
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


    public Ingresante (){
    }

    public void Coartada(){

    }
}
