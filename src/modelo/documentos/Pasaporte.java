package modelo.documentos;

import java.time.LocalDate;

import modelo.TipoClan;

public class Pasaporte extends Documento {
    private LocalDate fecha_nacimiento;
    private String raza;
    private String huella;
    private TipoClan clanEmisor;
    private String foto;
    private String plantillaPasaporte;

       public Pasaporte(String nombre, String origen, boolean valido, LocalDate vencimiento, LocalDate fecha_nacimiento, String raza, String huella, TipoClan clanEmisor, String foto, String plantillaPasaporte) {
        super(nombre, origen, valido, vencimiento);
            if (fecha_nacimiento == null) {
                throw new IllegalArgumentException("fecha_nacimiento no puede estar vacio ni ser nulo");
            }
            if (raza == null || raza.isBlank()) {
                throw new IllegalArgumentException("La raza no puede estar vacía ni ser nula");
            }
            if (huella == null || huella.isBlank()) {
                throw new IllegalArgumentException( "La ruta de la huella es obligatoria.");
            }

            this.fecha_nacimiento = fecha_nacimiento;
            this.raza = raza;
            this.huella = huella;
            this.clanEmisor = clanEmisor;
            this.foto = foto;
            this.plantillaPasaporte = plantillaPasaporte;
    }

    public LocalDate getFechaNacimiento() {
        return this.fecha_nacimiento;
    }

    public String getRaza() {
        return this.raza;
    }

    public String getHuella() {
        return this.huella;
    }

    public TipoClan getClanEmisor() {
        return this.clanEmisor;
    }

    public String getFoto() {
        return this.foto;
    }

    public String getPlantillaPasaporte() {
        return this.plantillaPasaporte;
    }
    
   @Override
    public String obtenerDetalles() {
        return "Pasaporte: " + getNombre() + ", Origen: " + getOrigen() + ", Raza: " + getRaza() + ", Válido: " + isValido() 
            + ", Fecha de Nacimiento: " + this.getFechaNacimiento() + ", Clan emisor: " + getClanEmisor()
                + ", Vencimiento: " + getFechaVencimiento() + ", Válido: " + isValido();
    }
}

 
