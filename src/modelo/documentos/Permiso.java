package modelo.documentos;

import java.time.LocalDate;
import modelo.TipoClan;

public class Permiso extends Documento {
    private String dni;
    private String ocupacion;
    private int num_permiso;
    // Clan que figura como emisor del permiso
    // La idea es que el clan del personaje y el clan que figura en su permiso puedan ser diferentes 
    // Esto nos servirá más adelante para las falsificaciones
    private final TipoClan clanEmisor;

    public Permiso(String nombre, String origen, boolean valido, LocalDate vencimiento, String dni, String ocupacion, int num_permiso, TipoClan clanEmisor) {
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
        // Validación del nuevo atributo.
        if (clanEmisor == null) {
            throw new IllegalArgumentException( "El clan emisor no puede ser nulo");
        }

        this.dni = dni;
        this.ocupacion = ocupacion;
        this.num_permiso = num_permiso;
        this.clanEmisor = clanEmisor;
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

    // Getter del clan emisor.
    public TipoClan getClanEmisor() {
        return this.clanEmisor;
    }


    @Override
    public String obtenerDetalles() {
        return "Permiso: " + getNombre() + ", Origen: " + getOrigen() + ", Válido: " + isValido() +
               ", DNI: " + dni + ", Ocupación: " + ocupacion + ", Número de Permiso: " + num_permiso + ", Fecha de Vencimiento: " + this.getFechaVencimiento() + ", Clan Emisor: " + clanEmisor;
    }
}
