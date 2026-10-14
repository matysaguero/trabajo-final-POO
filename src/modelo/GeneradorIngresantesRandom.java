package modelo;

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
    private static final String[] DOMICILIOS = {"Ruta 25 de Mayo", "Av. Rivadavia 123", "Sector 7G", "Calle Falsa 123"};
    private static final String[] SEXOS = {"Masculino", "Femenino", "No binario", "Otro"};

    private final Random random = new Random();

    public Ingresante generarIngresante(int numIdBase) {
        String nombre = NOMBRES[random.nextInt(NOMBRES.length)];
        int altura = 150 + random.nextInt(45); // Entre 150 y 194 cm
        int peso = 50 + random.nextInt(50);    // Entre 50 y 99 kg
        double reputacion = 1.0 + random.nextInt(10); //Entre 10  y 11.0

        TipoIngresante tipo = TipoIngresante.values()[random.nextInt(TipoIngresante.values().length)];
        TipoRaza raza = TipoRaza.values()[random.nextInt(TipoRaza.values().length)];
        TipoClan clan = TipoClan.values()[random.nextInt(TipoClan.values().length)];
        TipoCiudad ciudad = TipoCiudad.values()[random.nextInt(TipoCiudad.values().length)];
        String rutaImagen = "assets/imagenes/ingresantes/caballero.png";

        // Generamos documentos para el ingresante
        ArrayList<Documento> documentos = new ArrayList<>();
        boolean pasaporteTrucho = random.nextBoolean();
        boolean dniTrucho = random.nextBoolean();

        Pasaporte pasaporte = new Pasaporte("15/10/2027", numIdBase, peso, altura, 
            PAISES[random.nextInt(PAISES.length)], ciudad.name(), pasaporteTrucho);

        DNI dni = new DNI(
            "20/12/2028",
            numIdBase + 1000,
            dniTrucho,
            "HUELLA-" + (1000 + random.nextInt(9000)),
            ciudad.name(),
            SEXOS[random.nextInt(SEXOS.length)],
            DOMICILIOS[random.nextInt(DOMICILIOS.length)]
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
}