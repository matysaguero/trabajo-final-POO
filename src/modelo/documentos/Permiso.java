package modelo.documentos;

public class Permiso extends Documento {
    private String dni;
    private String ocupacion;
    private int num_permiso;
    private String fecha_vencimiento;

    public Permiso(String nombre, String origen, boolean valido, String dni, String ocupacion, int num_permiso, String fecha_vencimiento) {
        super(nombre, origen, valido);
        if (dni == null || dni.isBlank()) {
            throw new IllegalArgumentException("dni no puede estar vacio ni ser nulo");
        }
        if (ocupacion == null || ocupacion.isBlank()) {
            throw new IllegalArgumentException("ocupacion no puede estar vacio ni ser nulo");
        }
        if (num_permiso <= 0) {
            throw new IllegalArgumentException("num_permiso no puede ser menor o igual a cero");
        }
        if (fecha_vencimiento == null || fecha_vencimiento.isBlank()) {
            throw new IllegalArgumentException("fecha_vencimiento no puede estar vacio ni ser nulo");
        }

        this.dni = dni;
        this.ocupacion = ocupacion;
        this.num_permiso = num_permiso;
        this.fecha_vencimiento = fecha_vencimiento;
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

    public String getFechaVencimiento() {
        return this.fecha_vencimiento;
    }

    @Override
    public String obtenerDetalles() {
        return "Permiso: " + getNombre() + ", Origen: " + getOrigen() + ", Válido: " + isValido() +
               ", DNI: " + dni + ", Ocupación: " + ocupacion + ", Número de Permiso: " + num_permiso + ", Fecha de Vencimiento: " + fecha_vencimiento;
    }
}
