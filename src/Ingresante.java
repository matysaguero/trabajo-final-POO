public class Ingresante {
    protected String nombre;
    protected int altura;
    protected int peso;
    protected String clan;
    protected String ciudad_origen;

    public Ingresante(String nombre, int altura, int peso, String clan, String ciudad_origen){
        this.nombre = nombre;
        this.altura = altura;
        this.peso = peso;
        this.clan = clan;
        this.ciudad_origen = ciudad_origen;
    }
    
    public String getNombre(){
        return this.nombre;
    }

    public int getAltura(){
        return this.altura;
    }

    public int getPeso(){
        return this.peso;
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
