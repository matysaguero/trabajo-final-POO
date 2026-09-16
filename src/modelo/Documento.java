package modelo;

public abstract class Documento {
    protected String fechaVencimiento;
    protected int numId;
    protected boolean trucho;

    public Documento(String fechaVencimiento, int numId, boolean trucho){
        if (fechaVencimiento == null || fechaVencimiento.isBlank()) {
            throw new IllegalArgumentException("La fecha de vencimiento no puede ser negativa ni igual a cero");
        }  
        if (numId <= 0 ){
            throw new IllegalArgumentException("el numero de id no puede ser negativo ni igual a cero");
        }
    
    this.fechaVencimiento = fechaVencimiento;
    this.numId = numId;
    }

    public String getFechaVencimiento(){
    return this.fechaVencimiento;
    } 

    public int getNumId(){
        return this.numId;
    }

    public boolean getTrucho(){
        return this.trucho;
    }

    public void mostrarDetalle(){
        System.out.println("La fecha de vencimiento es: "+ fechaVencimiento);
        System.out.println("El numero de id es: "+ numId);
        System.out.println("");

    }
}
