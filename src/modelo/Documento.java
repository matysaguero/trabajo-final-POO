package modelo;

public abstract class Documento {
    protected String fechaVencimiento;
    protected String emisor;
    protected String foto;
    protected int numId;
    protected boolean trucho;

    public Documento(String fechaVencimiento, String emisor, String foto, int numId, boolean trucho){
        if (fechaVencimiento == null || fechaVencimiento.isBlank()) {
            throw new IllegalArgumentException("La fecha de vencimiento no puede ser negativa ni igual a cero");
        }
        if (emisor == null || emisor.isEmpty()){
            throw new IllegalArgumentException("el emisor no puede ser nulo ni vacio");
        }
        if (foto == null || foto.isEmpty()){
            throw new IllegalArgumentException("la foto no puede ser nula ni vacia");
        }  
        if (numId <= 0 ){
            throw new IllegalArgumentException("el numero de id no puede ser negativo ni igual a cero");
        }
    
    this.fechaVencimiento = fechaVencimiento;
    this.emisor = emisor;
    this.foto = foto;
    this.numId = numId;
    }

    public String getFechaVencimiento(){
    return this.fechaVencimiento;
    } 

    public String getEmisor(){
        return this.emisor;
    }

    public String getFoto(){
        return this.foto;
    }

    public int getNumId(){
        return this.numId;
    }

    public boolean getTrucho(){
        return this.trucho;
    }

    public void mostrarDetalle(){
        System.out.println("La fecha de vencimiento es:"+ fechaVencimiento);
        System.out.println("El emisor es:"+ emisor);
        System.out.println("La foto es: (agregar descripcion)"+ foto);
        System.out.println("El numero de id es:"+ numId);
        System.out.println("");

    }
}
