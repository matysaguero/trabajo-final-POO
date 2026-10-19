package modelo.documentos;

import java.time.LocalDate;

public abstract class Documento {
    private String nombre;
    private String origen;
    private boolean valido;
    private LocalDate vencimiento;

    public Documento(String nombre, String origen, boolean valido, LocalDate vencimiento) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("nombre no puede estar vacio ni ser nulo.");
        }
        if (origen == null || origen.isBlank()) {
            throw new IllegalArgumentException("origen no puede estar vacio ni ser nulo.");
        }
        if (vencimiento == null) {
            throw new IllegalArgumentException("La fecha de vencimiento es obligatoria.");
        }
        this.nombre = nombre;
        this.origen = origen;
        this.valido = valido;
    }

    public String getNombre() { return this.nombre; }

    public String getOrigen() { return this.origen; }

    public boolean isValido() { return this.valido; }

    public LocalDate getFechaVencimiento() { return this.vencimiento; }

    public abstract String obtenerDetalles();

}
