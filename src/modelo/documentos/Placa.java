package modelo.documentos;

public class Placa {
    private String nombre;
    private String foto;

    public Placa(String nombre, String foto) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("nombre no puede estar vacio ni ser nulo");
        }
        if (foto == null || foto.isBlank()) {
            throw new IllegalArgumentException("foto no puede estar vacio ni ser nulo");
        }

        this.nombre = nombre;
        this.foto = foto;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getFoto() {
        return this.foto;
    }

    public String obtenerDetalles() {
        return "Placa: " + getNombre() + ", Foto: " + getFoto();
    }

}