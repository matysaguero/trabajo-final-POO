package modelo.Ingresantes;

import java.util.ArrayList;
import java.util.List;

import modelo.TipoCiudad;
import modelo.TipoClan;
import modelo.TipoIngresante;
import modelo.TipoRaza;
import modelo.documentos.Documento;
import modelo.documentos.ResumenDocumental;

public abstract class Ingresante { // Añadi abstract porque al usar el metodo "public abstract String responderInterrogatorio()" y colocarlo en "Enemigo.java" es una condicion obligatoria aclarar que ahora la clase "ingreante.java" es abstracta. --07/09/26 , 20:48hs
    private String nombreDeclarado;
    private ArrayList<Documento> documentos;    
    private TipoIngresante tipo; 
    private TipoRaza raza;
    private TipoClan clan;
    private TipoCiudad ciudad;
    private String rutaImagen;
    private String motivoVisita;

    public Ingresante(String nombreDeclarado, ArrayList<Documento> documentos, TipoIngresante tipo, TipoRaza raza, TipoClan clan, TipoCiudad ciudad, String rutaImagen, String motivoVisita){
        if (nombreDeclarado == null || nombreDeclarado.isEmpty()){
            throw new IllegalArgumentException("El nombre declarado no puede ser nulo ni vacio.");
        }
        
        if (tipo == null) {
            throw new IllegalArgumentException("El tipo de ingresante no puede ser nulo.");
        }

        if (raza == null) {
            throw new IllegalArgumentException( "La raza del ingresante no puede ser nula.");
        }

        if (clan == null) {
            throw new IllegalArgumentException("El clan del ingresante no puede ser nulo.");
        }

        if (ciudad == null) {
            throw new IllegalArgumentException("La ciudad del ingresante no puede ser nula.");
        }


        this.nombreDeclarado = nombreDeclarado;
        this.tipo = tipo;
        this.raza= raza;
        this.clan = clan;
        this.ciudad = ciudad;
        this.rutaImagen = rutaImagen;
        this.motivoVisita = motivoVisita;

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

    public String getRutaImagen(){
        return this.rutaImagen;
    }
    
    public List<Documento> getDocumentos() { // Getter para los documentos.
        return List.copyOf(documentos);
    }

    public String responderInterrogatorio(){
        return this.motivoVisita;
    }

    public ResumenDocumental obtenerResumenDocumental() {
    ResumenDocumental resumen = new ResumenDocumental();
        for (Documento documento : documentos) {
            documento.aportarEvidencias(resumen);
        }
    return resumen;
    }
    /* 
    El ingresante crea un resumen vacío.
    Recorre sus documentos como objetos de tipo Documento.
    Invoca aportarEvidencias() en cada documento.
    Java ejecuta automáticamente el método de la subclase correspondiente.
    Devuelve el resumen para que lo utilicen las políticas
    */

    public abstract String darPresentacion();
    public abstract boolean esAmenaza();

}

