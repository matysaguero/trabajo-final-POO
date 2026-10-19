package modelo.documentos;

import java.time.LocalDate;

public class DNI extends Documento {
    private String PLANTILLA_DNI;
    private LocalDate fecha_nacimiento;
    private int nro_dni;
    private String huella;
    private String foto;
    private String raza;

    public DNI(String PLANTILLA_DNI, String nombre, String origen, boolean valido,
               LocalDate vencimiento, LocalDate fecha_nacimiento, int nro_dni, 
               String huella, String foto, String raza) {
        super(nombre, origen, valido, vencimiento);

        if (PLANTILLA_DNI == null || PLANTILLA_DNI.isBlank()) {
            throw new IllegalArgumentException("La plantilla no es válida");
        }
        if (fecha_nacimiento == null) {
            throw new IllegalArgumentException("La fecha de nacimiento es obligatoria.");
        }
        if (nro_dni <= 0) {
            throw new IllegalArgumentException("El número de DNI no es válido");
        }
        if (vencimiento == null) {
            throw new IllegalArgumentException("La fecha de vencimiento no es válida");
        }
        if (huella == null || huella.isBlank()) {
            throw new IllegalArgumentException("La huella no es válida");
        }
        if (foto == null || foto.isBlank()) {
            throw new IllegalArgumentException("La foto no es válida");
        }
        if (raza == null || raza.isBlank()) {
            throw new IllegalArgumentException("La raza no es válida");
        }

        this.PLANTILLA_DNI = PLANTILLA_DNI;
        this.fecha_nacimiento = fecha_nacimiento;
        this.nro_dni = nro_dni;
        this.huella = huella;
        this.foto = foto;
        this.raza = raza;
    }

    public String getPlantillaDni() { return this.PLANTILLA_DNI; }

    public LocalDate getFechaNacimiento() { return this.fecha_nacimiento; }

    public int getNroDni() { return this.nro_dni; }

    public String getHuella() { return this.huella; }

    public String getFoto() { return this.foto; }

    public String getRaza() { return this.raza; }

    @Override
    public String obtenerDetalles() {
        return "DNI [Nro: " + nro_dni + ", Raza: " + raza +
               ", Nacimiento: " + fecha_nacimiento + ", Vencimiento: " + this.getFechaVencimiento() + "]";
    }
}