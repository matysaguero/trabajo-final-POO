package modelo;

import java.util.ArrayList;

public class Enemigo extends Ingresante{

    protected boolean portaBomba;

    public Enemigo(String nombreDeclarado, int alturaVisual, int pesoEnBalanza, String clan, String ciudadOrigen, double reputacion, ArrayList<Documento> documentos, TipoIngresante tipo, boolean portaBomba){
        super(nombreDeclarado, alturaVisual, pesoEnBalanza, clan, ciudadOrigen, reputacion, documentos, tipo);
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