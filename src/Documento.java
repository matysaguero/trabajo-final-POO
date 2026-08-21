public abstract class Documento {
    protected int fecha_vencimiento;
    protected String emisor;
    protected String foto;
    protected int num_id;
    
    public Documento(int fecha_vencimiento, String emisor, String foto, int num_id){
        if (fecha_vencimiento <= 0) {
            throw new IllegalArgumentException("La fecha de vencimiento no puede ser negativa ni igual a cero");
        }
        if (emisor == null || emisor.isEmpty()){
            throw new IllegalArgumentException("el emisor no puede ser nulo ni vacio");
        }
        if (foto == null || foto.isEmpty()){
            throw new IllegalArgumentException("la foto no puede ser nula ni vacia");
        }  
        if (num_id <= 0 ){
            throw new IllegalArgumentException("el numero de id no puede ser negativo ni igual a cero");
        }
    }
}
