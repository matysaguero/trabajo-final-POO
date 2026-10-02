package modelo.Ingresantes;

import java.util.ArrayList;

import modelo.TipoCiudad;
import modelo.TipoClan;
import modelo.TipoIngresante;
import modelo.TipoRaza;
import modelo.documentos.Documento;

public abstract class Ingresante { // Añadi abstract porque al usar el metodo "public abstract String responderInterrogatorio()" y colocarlo en "Enemigo.java" es una condicion obligatoria aclarar que ahora la clase "ingreante.java" es abstracta. --07/09/26 , 20:48hs
    private String nombreDeclarado;
    private int alturaVisual;
    private int pesoEnBalanza;
    private Double reputacion;
    private ArrayList<Documento> documentos;    
    private TipoIngresante tipo; 
    private TipoRaza raza;
    private TipoClan clan;
    private TipoCiudad ciudad;

    public Ingresante(String nombreDeclarado, int alturaVisual, int pesoEnBalanza, double reputacion, ArrayList<Documento> documentos, TipoIngresante tipo, TipoRaza raza, TipoClan clan, TipoCiudad ciudad){
        if (nombreDeclarado == null || nombreDeclarado.isEmpty()){
            throw new IllegalArgumentException("El nombre declarado no puede ser nulo ni vacio.");
        }
        if (alturaVisual <= 0){
            throw new IllegalArgumentException("La altura declarada no puede ser cero ni negativa.");
        }
        if (pesoEnBalanza <= 0){
            throw new IllegalArgumentException("El peso no puede ser cero ni negativo.");
        }
        if (reputacion <= 0) {
            throw new IllegalArgumentException("La reputacion debe ser mayor a 0 para poder manejar de manera mas sencilla la gestion de esta.");
        }
        this.nombreDeclarado = nombreDeclarado;
        this.alturaVisual = alturaVisual;
        this.pesoEnBalanza = pesoEnBalanza;
        this.reputacion = reputacion;
        //this.documentos = documentos;
        this.tipo = tipo;
        this.raza= raza;
        this.clan = clan;
        this.ciudad = ciudad;
        // Si no llegan documentos (null), se usa una lista vacía para que mostrarDetalle()
        // no falle. Se guarda una copia para que la lista original no pueda modificarse desde afuera.
        if (documentos == null) {
            this.documentos = new ArrayList<>();
            } else {
                this.documentos = new ArrayList<>(documentos);
            }   
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

    public TipoIngresante getTipoIngresante(){
        return this.tipo;
    }

    public TipoRaza getRaza(){
        return this.raza;
    }

    public TipoClan getClan(){
        return this.clan;
    }

    public TipoCiudad getCiudadOrigen(){
        return this.ciudad;
    }

    public void mostrarDetalle(){
        System.out.println("======== DECLARACIÓN ========");
        System.out.println("Mi nombre es: "+ nombreDeclarado);
        System.out.println("Mi altura es: "+ alturaVisual+"cm");
        System.out.println("El peso muestra: "+ pesoEnBalanza+"kg");
        System.out.println("Soy de: "+ clan +", y vengo de: " + ciudad);
        System.out.println("Tipo de ingresante: "+ tipo);
        System.out.println("Mi raza es: "+ raza);
        System.out.println("");
        System.out.println("");

        for (int i = 0; i < documentos.size(); i++) {
            Documento documento = documentos.get(i);
            documento.mostrarDetalle();
        }

    }

    public abstract String responderInterrogatorio();
    public abstract String darPresentacion();
    public abstract boolean esAmenaza(); //NUEVO

    //Se agrega a la clse abstracta tipodeIngresante como atrbuto, en el constructor y dentro de mostrarDetalle() 25/8/26 21:20pm.
    
 // Métodos abstractos para la interacción, comentados porque dan problemas, necesito instanciarlos en clases hijas y no estan desarrollados todavia.

//COMO APLICAR METODOS EN ESTE TIPO DE JUEGO?

 /*public abstract String darPresentacion();
*/

}

