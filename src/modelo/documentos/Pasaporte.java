package modelo.documentos;

public class Pasaporte extends Documento {
    private String fecha_nacimiento;
    private int raza;
    private int huella;

    public Pasaporte(String nombre, String origen, boolean valido, String fecha_nacimiento, int raza, int huella) {
        super(nombre, origen, valido);
        if (fecha_nacimiento == null || fecha_nacimiento.isBlank()) {
            throw new IllegalArgumentException("fecha_nacimiento no puede estar vacio ni ser nulo");
        }
        if (raza <= 0) {
            throw new IllegalArgumentException("raza no puede ser menor o igual a cero");
        }
        if (huella <= 0) {
            throw new IllegalArgumentException("huella no puede ser menor o igual a cero");
        }

        this.fecha_nacimiento = fecha_nacimiento;
        this.raza = raza;
        this.huella = huella;
    }

    public String getFechaNacimiento() {
        return this.fecha_nacimiento;
    }

    public int getRaza() {
        return this.raza;
    }

    public int getHuella() {
        return this.huella;
    }
    
   @Override
    public String obtenerDetalles() {
        return "Pasaporte: " + getNombre() + ", Origen: " + getOrigen() + ", Válido: " + isValido() +
               ", Fecha de Nacimiento: " + fecha_nacimiento +
               ", Raza: " + raza + ", Huella: " + huella;
    }

}
 /*   
    public Pasaporte(String fechaVencimiento, int numId, int peso, int altura, String pais, String ciudad, boolean trucho){
        super(fechaVencimiento, numId, trucho);
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

*/ 

 
