/*package modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import modelo.Ingresantes.Enemigo;
import modelo.Ingresantes.Ingresante;
import modelo.Ingresantes.IngresanteRegular;
import modelo.documentos.DNI;
import modelo.documentos.Documento;
import modelo.documentos.Pasaporte;

public class GeneradorIngresantesRandom {

    private static final String[] NOMBRES = {"Andrea", "Marcos", "Javier", "Elena", "Kael", "Lyra", "Tarius", "Valeria", "Dante", "Sora"};
    private static final String[] PAISES = {"Alfheim", "Utopía", "Confederación Solar", "República Austral de Gaalad"};

    private final Random random = new Random();

    public Ingresante generarIngresante(int numIdBase) {
        String nombre = NOMBRES[random.nextInt(NOMBRES.length)];
        int altura = 150 + random.nextInt(45); // Entre 150 y 194 cm
        int peso = 50 + random.nextInt(50);    // Entre 50 y 99 kg
        double reputacion = 1.0 + random.nextInt(10);

        TipoIngresante tipo = TipoIngresante.values()[random.nextInt(TipoIngresante.values().length)];
        TipoRaza raza = TipoRaza.values()[random.nextInt(TipoRaza.values().length)];
        TipoClan clan = TipoClan.values()[random.nextInt(TipoClan.values().length)];
        TipoCiudad ciudad = TipoCiudad.values()[random.nextInt(TipoCiudad.values().length)];
        String rutaImagen = "assets/imagenes/ingresantes/caballero.png";

        // Generamos documentos compatibles con las clases actuales de modelo.documentos
        ArrayList<Documento> documentos = new ArrayList<>();
        boolean pasaporteValido = random.nextBoolean();
        boolean dniValido = random.nextBoolean();

        String origenPais = PAISES[random.nextInt(PAISES.length)];
        
        //Se genera una fecha de nacimiento random.
        LocalDate fechaNacimiento = generarFechas(LocalDate.of(2080, 1, 1), LocalDate.of(2120, 12, 31));
        LocalDate fechaVencimiento = generarFechas(LocalDate.of(2157, 1, 1), LocalDate.of(2160, 12, 31));
        
        
        int codigoRaza = raza.ordinal() + 1; // Siempre > 0
        int codigoHuella = 1000 + random.nextInt(9000); // Siempre > 0

        Pasaporte pasaporte = new Pasaporte(
            nombre,
            origenPais,
            pasaporteValido,
            fechaVencimiento,
            fechaNacimiento,
            codigoRaza,
            codigoHuella
        );

        DNI dni = new DNI(
            "assets/imagenes/documento.png",
            nombre,
            ciudad.name(),
            dniValido,
            fechaNacimiento,
            numIdBase + 1000,
            fechaVencimiento,
            "HUELLA-" + codigoHuella,
            rutaImagen,
            raza.name()
        );

        documentos.add(pasaporte);
        documentos.add(dni);

        // 30% de probabilidad de que sea un Enemigo (amenaza)
        boolean esEnemigo = random.nextInt(100) < 30;
        if (esEnemigo) {
            boolean portaBomba = random.nextBoolean();
            return new Enemigo(nombre, altura, peso, reputacion, documentos, tipo, raza, clan, ciudad, rutaImagen, portaBomba);
        } else {
            return new IngresanteRegular(nombre, altura, peso, reputacion, documentos, tipo, raza, clan, ciudad, rutaImagen);
        }
    }

    public List<Ingresante> generarLista(int cantidad) {
        List<Ingresante> lista = new ArrayList<>();
        for (int i = 1; i <= cantidad; i++) {
            lista.add(generarIngresante(i));
        }
        return lista;
    }

    // Mi generador de fechas ramdom, al metodo le ingresa el rango de fechas en la cual queres generar.
    public LocalDate generarFechas(LocalDate inicio, LocalDate fin){
        long diasInicio = inicio.toEpochDay();
        long diasFin = fin.toEpochDay();

        long diasAleatorio = diasInicio + random.nextLong(diasFin - diasInicio);
        return LocalDate.ofEpochDay(diasAleatorio);
    }
}
    */