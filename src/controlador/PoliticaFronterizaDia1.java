package controlador;

import modelo.TipoCiudad;
import modelo.TipoClan;
import modelo.TipoRaza;

public class PoliticaFronterizaDia1 extends PoliticaFronteriza{
    private TipoRaza raza;

    public PoliticaFronterizaDia1(String fecha, TipoCiudad ciudadPermitida, TipoClan clanProhibido, TipoRaza raza){
        super(fecha, ciudadPermitida, clanProhibido);
        if (raza == null){
            throw new IllegalArgumentException("Ingrese una raza valida.");
        }
        this.raza = raza;
    }


    public TipoRaza getRaza(){
        return this.raza;
    }


@Override 
    public void mostrarDetalle(){
        System.out.println("======== REGLAS DEL DIA 1========");
        System.out.println("SOLO PASAN: " + raza+"S!!!");
        System.out.println("SIN EXCEPCION");
        System.out.println("AL QUE NO SEA HUMANO SE LE PROHIBE LA ENTRADA!");
        
    }
}
