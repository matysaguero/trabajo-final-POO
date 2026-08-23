public abstract class Documento {
    protected String fechaVencimiento;
    protected String emisor;
    protected String foto;
    protected int numId;
    
    public Documento(String fechaVencimiento, String emisor, String foto, int numId){
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
    }
}
