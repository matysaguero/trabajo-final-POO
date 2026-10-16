package modelo.documentos;

public class DNI extends Documento {
    private static final String PLANTILLA_DNI = "assets/imagenes/documento.png";
    private String raza;
    private String fecha_nacimiento;
    private int nro_dni;
    private String huella;
    private String vencimiento;
    private String foto;

public DNI(String PLANTILLA_DNI, String nombre, String origen, boolean valido, String fecha_nacimiento, int nro_dni, String vencimiento, String huella, String foto, String raza) {
        super(nombre, origen, valido);
        if (raza == null || raza.isBlank()) {
            throw new IllegalArgumentException("raza no puede estar vacio ni ser nulo");
        }
        if (fecha_nacimiento == null || fecha_nacimiento.isBlank()) {
            throw new IllegalArgumentException("fecha_nacimiento no puede estar vacio ni ser nulo");
        }
        if (vencimiento == null || vencimiento.isBlank()) {
            throw new IllegalArgumentException("vencimiento no puede estar vacio ni ser nulo");
        }
        if (foto == null || foto.isBlank()) {
            throw new IllegalArgumentException("foto no puede estar vacio ni ser nulo");
        }

        this.raza = raza;
        this.fecha_nacimiento = fecha_nacimiento;
        this.nro_dni = nro_dni;
        this.vencimiento = vencimiento;
        this.foto = foto;
        this.huella = huella;
    }

    public String getRaza() {
        return this.raza;
    }

    public String getFechaNacimiento() {
        return this.fecha_nacimiento;
    }

    public int getNroDni() {
        return this.nro_dni;
    }

    public String getVencimiento() {
        return this.vencimiento;
    }

    public String getFoto() {
        return this.foto;
    }

    public String getHuella() {
        return this.huella;
    }

    @Override
    public String obtenerDetalles() {
        return "DNI: " + getNombre() + ", Origen: " + getOrigen() + ", Válido: " + isValido() +
               ", Raza: " + raza + ", Fecha de Nacimiento: " + fecha_nacimiento +
               ", Número de DNI: " + nro_dni + ", Vencimiento: " + vencimiento +
               ", Huella: " + huella + ", Foto: " + foto;
    }
}    
    /* 
    public DNI(String fechaVencimiento, int numId, boolean trucho, String huella, String lugarNacimiento, String raza, String domicilio){
        super(fechaVencimiento, numId, trucho);
        
        if (huella == null || huella.isBlank()) {
            throw new IllegalArgumentException("huella no puede estar vacio ni ser nulo");    
        }
        if (lugarNacimiento == null || lugarNacimiento.isBlank()) {
            throw new IllegalArgumentException("lugarNacimiento no puede estar vacio ni ser nulo");
        }
        if (raza == null || raza.isBlank()) {
            throw new IllegalArgumentException("raza no puede estar vacio ni ser nulo");
        }
        if (domicilio == null || domicilio.isBlank()) {
            throw new IllegalArgumentException("domicilio no puede estar vacio ni ser nulo");
        }

        this.huella = huella;
        this.lugarNacimiento = lugarNacimiento;
        this.raza = raza;
        this.domicilio = domicilio;
    }

    public String getHuella(){
        return this.huella;
    }

    public String getLugarNacimiento(){
        return this.lugarNacimiento;
    }

    public String getRaza(){
        return this.raza;
    }

    public String getDomicilio(){
        return this.domicilio;
    }
*/
