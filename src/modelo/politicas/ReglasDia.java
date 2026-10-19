package modelo.politicas;

import java.time.LocalDate;
import java.util.List;

import modelo.TipoCiudad;
import modelo.TipoClan;
import modelo.TipoRaza;

public abstract class ReglasDia {
    private final int numeroDia;
    private final String descripcion;
    private final LocalDate fechaDia;

    public ReglasDia(int numeroDia, String descripcion, LocalDate fechaDia) {
        if (numeroDia <= 0) {
            throw new IllegalArgumentException("El número de día debe ser mayor a cero.");
        }
        if (descripcion == null || descripcion.isBlank()) {
            throw new IllegalArgumentException("La descripción de las reglas es obligatoria.");
        }
        if (fechaDia == null) {
            throw new IllegalArgumentException("Debe estar cargada la fecha del dia.");
        }
        this.fechaDia = fechaDia;
        this.numeroDia = numeroDia;
        this.descripcion = descripcion;
    }

    public int getNumeroDia() { return this.numeroDia; }

    public String getDescripcion() { return this.descripcion; }

    public LocalDate getFechaDia() { return this.fechaDia; }

    // Métodos ABSTRACTOS: la clase abstracta declara qué restricciones existen, 
    // pero cada día hijo define sus propias listas o políticas específicas.
    public abstract List<TipoCiudad> getCiudadesProhibidas();
    public abstract List<TipoClan> getClanesProhibidos();
    public abstract List<TipoRaza> getRazasProhibidas();
}