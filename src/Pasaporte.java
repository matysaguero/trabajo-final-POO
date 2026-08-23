public class Pasaporte extends Documento {
    protected int peso;
    protected int altura;
    protected String pais;
    protected String ciudad;

    public Pasaporte(String fechaVencimiento, String emisor, String foto, int numId, int peso, int altura, String pais, String ciudad){
        super(fechaVencimiento, emisor, foto, numId);
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
        
        this.peso = peso;
        this.altura = altura;
        this.pais = pais;
        this.ciudad = ciudad;
    }


    public int getPeso(){
        return this.peso;
    }

    public int getAltura(){
        return this.altura;
    }

    public String getPais(){
        return this.pais;
    }

    public String getCiudad(){
        return this.ciudad;
    }

    public void mostrarDetalle(){
        super.mostrarDetalle();
        System.out.println("Peso: "+ peso + "kg.");
        System.out.println("Altura: "+ altura+ "cm");
        System.out.println("Pais: "+ pais);
        System.out.println("Ciudad: "+ ciudad);
    }
}
