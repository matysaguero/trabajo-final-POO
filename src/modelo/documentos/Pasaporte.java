package modelo.documentos;

import java.time.LocalDate;

public class Pasaporte extends Documento {
    private LocalDate fecha_nacimiento;
    private int raza;
    private int huella;

    public Pasaporte(String nombre, String origen, boolean valido, LocalDate vencimiento, LocalDate fecha_nacimiento, int raza, int huella) {
        super(nombre, origen, valido, vencimiento);
        if (fecha_nacimiento == null) {
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

    public LocalDate getFechaNacimiento() {
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

 
