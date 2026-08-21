public class Pasaporte extends Documento {
    protected int peso;
    protected int altura;
    protected String pais;
    protected String ciudad;

    public Pasaporte(int fecha_vencimiento, String emisor, String foto, int num_id, int peso, int altura, String pais, String ciudad, String fechaVencimiento){
        super(fecha_vencimiento, emisor, foto, num_id);
        if (peso <= 0) {
            throw new IllegalArgumentException("Peso no valido");
        }
        if (altura <= 0){
            throw new IllegalArgumentException("Altura no valida");
        }
        if (pais == null || pais.isBlank()){
            throw new IllegalArgumentException("Pais no valido");
        }
        if (ciudad == null || ciudad.isBlank()){
            throw new IllegalArgumentException("Ciudad no valida");
        }
    }

}
