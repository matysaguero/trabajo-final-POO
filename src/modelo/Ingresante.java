package modelo;

import java.util.ArrayList;

public abstract class Ingresante { // Añadi abstract porque al usar el metodo "public abstract String responderInterrogatorio()" y colocarlo en "Enemigo.java" es una condicion obligatoria aclarar que ahora la clase "ingreante.java" es abstracta. --07/09/26 , 20:48hs
    protected String nombreDeclarado;
    private TipoIngresante tipo; 
    protected int alturaVisual;
    protected int pesoEnBalanza;
    protected String clan;
    protected String ciudadOrigen;
    protected Double reputacion;
    protected ArrayList<Documento> documentos;    

    public Ingresante(String nombreDeclarado, int alturaVisual, int pesoEnBalanza, String clan, String ciudadOrigen, double reputacion, ArrayList<Documento> documentos){
        if (nombreDeclarado == null || nombreDeclarado.isEmpty()){
            throw new IllegalArgumentException("El nombre declarado no puede ser nulo ni vacio.");
        }
        if (alturaVisual <= 0){
            throw new IllegalArgumentException("La altura declarada no puede ser cero ni negativa.");
        }
        if (pesoEnBalanza <= 0){
            throw new IllegalArgumentException("El peso no puede ser cero ni negativo.");
        }
        if (clan == null || clan.isEmpty()){
            throw new IllegalArgumentException("El clan no puede ser nulo ni vacio.");
        }
        if (ciudadOrigen == null || ciudadOrigen.isEmpty()){
            throw new IllegalArgumentException("La Ciudad de Origen no puede ser nula ni vacia (Ingresar con formato CiudadOrigen).");
        }
        if (reputacion <= 0) {
            throw new IllegalArgumentException("La reputacion debe ser mayor a 0 para poder manejar de manera mas sencilla la gestion de esta.");
        }
        this.nombreDeclarado = nombreDeclarado;
        this.alturaVisual = alturaVisual;
        this.pesoEnBalanza = pesoEnBalanza;
        this.clan = clan;
        this.ciudadOrigen = ciudadOrigen;
        this.reputacion = reputacion;
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

    public TipoIngresante getTipoIngresante(){
        return this.tipo;
    }

    public void mostrarDetalle(){
        System.out.println("======== DECLARACIÓN ========");
        System.out.println("Mi nombre es: "+ nombreDeclarado);
        System.out.println("Mi altura es: "+ alturaVisual+"cm");
        System.out.println("Tipo de ingresante: "+ tipo);
        System.out.println("El peso muestra: "+ pesoEnBalanza+"kg");
        System.out.println("Soy de: "+ clan +", y vengo de:" + ciudadOrigen);
        System.out.println("");
        System.out.println("");

        for (int i = 0; i < documentos.size(); i++) {
            Documento documento = documentos.get(i);
            documento.mostrarDetalle();
        }

    }

    public abstract String responderInterrogatorio();

    //Se agrega a la clse abstracta tipodeIngresante como atrbuto, en el constructor y dentro de mostrarDetalle() 25/8/26 21:20pm.
    
 // Métodos abstractos para la interacción, comentados porque dan problemas, necesito instanciarlos en clases hijas y no estan desarrollados todavia.

//COMO APLICAR METODOS EN ESTE TIPO DE JUEGO?

 /*public abstract String darPresentacion();
*/

}

