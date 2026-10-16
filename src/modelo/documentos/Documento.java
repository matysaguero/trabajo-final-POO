package modelo.documentos;

public abstract class Documento {
    private String nombre;
    private String origen;
    private boolean valido;

    public Documento(String nombre, String origen, boolean valido) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("nombre no puede estar vacio ni ser nulo");
        }
        if (origen == null || origen.isBlank()) {
            throw new IllegalArgumentException("origen no puede estar vacio ni ser nulo");
        }

        this.nombre = nombre;
        this.origen = origen;
        this.valido = valido;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getOrigen() {
        return this.origen;
    }

    public boolean isValido() {
        return this.valido;
    }

    public abstract String obtenerDetalles();

}
