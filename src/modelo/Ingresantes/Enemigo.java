package modelo.Ingresantes;

import java.util.ArrayList;

import modelo.TipoCiudad;
import modelo.TipoClan;
import modelo.TipoIngresante;
import modelo.TipoRaza;
import modelo.documentos.Documento;

public class Enemigo extends Ingresante{

    private boolean portaBomba;

    public Enemigo(String nombreDeclarado, int alturaVisual, int pesoEnBalanza, double reputacion, ArrayList<Documento> documentos, TipoIngresante tipo, TipoRaza raza, TipoClan clan, TipoCiudad ciudad, String rutaImagen, boolean portaBomba){
        super(nombreDeclarado, alturaVisual, pesoEnBalanza, reputacion, documentos, tipo, raza, clan, ciudad, rutaImagen);
        this.portaBomba = portaBomba;
    }

    public boolean getportaBomba(){
        return this.portaBomba;
    }


    @Override
    public String responderInterrogatorio() {
        return "¡No tengo por que darte explicaciones, oficial!";
    }

    @Override 
    public String darPresentacion(){
        return "Hola, quiero ingresar al planeta";
    }

    @Override
    public boolean esAmenaza() { //NUEVO
        return true;
    }
}