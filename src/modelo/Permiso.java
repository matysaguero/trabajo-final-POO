package modelo;

public class Permiso extends Documento {
    private String sello;
    private String ocupacion;

    public Permiso(String fechaVencimiento, int numId, String sello, String ocupacion, boolean trucho){
        super(fechaVencimiento, numId, trucho);
        if (sello == null || sello.isBlank()){
            throw new IllegalArgumentException("Sello no valido");
        }
        this.sello = sello;
        this.ocupacion = ocupacion;
    }

    public String getSello(){
        return this.sello;
    }

    public String getOcupacion(){
        return this.ocupacion;
    }

    public void mostrarDetalle(){
        
        System.out.println("======== PERMISO ========:");
        System.out.println("Sello: "+ sello);
        System.out.println("Ocupación: "+ ocupacion);
        System.out.println("");
        
        super.mostrarDetalle();
    }

}
