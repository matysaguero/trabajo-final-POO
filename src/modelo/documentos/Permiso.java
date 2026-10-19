package modelo.documentos;

import java.time.LocalDate;

public class Permiso extends Documento {
    private String dni;
    private String ocupacion;
    private int num_permiso;

    public Permiso(String nombre, String origen, boolean valido, LocalDate vencimiento, String dni, String ocupacion, int num_permiso) {
        super(nombre, origen, valido, vencimiento);
        if (dni == null || dni.isBlank()) {
            throw new IllegalArgumentException("dni no puede estar vacio ni ser nulo");
        }
        if (ocupacion == null || ocupacion.isBlank()) {
            throw new IllegalArgumentException("ocupacion no puede estar vacio ni ser nulo");
        }
        if (num_permiso <= 0) {
            throw new IllegalArgumentException("num_permiso no puede ser menor o igual a cero");
        }

        this.dni = dni;
        this.ocupacion = ocupacion;
        this.num_permiso = num_permiso;
    }

    public String getDni() {
        return this.dni;
    }

    public String getOcupacion() {
        return this.ocupacion;
    }

    public int getNumPermiso() {
        return this.num_permiso;
    }


    @Override
    public String obtenerDetalles() {
        return "Permiso: " + getNombre() + ", Origen: " + getOrigen() + ", Válido: " + isValido() +
               ", DNI: " + dni + ", Ocupación: " + ocupacion + ", Número de Permiso: " + num_permiso + ", Fecha de Vencimiento: " + this.getFechaVencimiento();
    }
}
