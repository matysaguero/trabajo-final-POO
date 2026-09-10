package modelo;

import java.util.ArrayList;

public class Enemigo extends Ingresante{

    protected boolean portaBomba;

    public Enemigo(String nombreDeclarado, int alturaVisual, int pesoEnBalanza, double reputacion, ArrayList<Documento> documentos, TipoIngresante tipo, TipoRaza raza, TipoClan clan, TipoCiudad ciudad, boolean portaBomba){
        super(nombreDeclarado, alturaVisual, pesoEnBalanza, reputacion, documentos, tipo, raza, clan, ciudad);
        this.portaBomba = portaBomba;
    }

    public boolean getportaBomba(){
        return this.portaBomba;
    }

    public void mostrarDetalle(){
        super.mostrarDetalle();
    }

@Override
    public String responderInterrogatorio() {
        return "¡No tengo por que darte explicaciones, oficial!";
    }

}