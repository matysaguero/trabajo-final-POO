package modelo.politicas;

import java.util.List;
import java.util.Random;

public class FabricaReglasDia {
    private final Random random;

    public FabricaReglasDia() {
        this.random = new Random();
    }

    /**
     * Retorna una variación aleatoria entre las disponibles sin que Partida
     * tenga que conocer las clases concretas ni contener un switch.
     */
    public ReglasDia crearVariacionAleatoria() {
        List<ReglasDia> variaciones = List.of(
            new ReglasVariacion1(),
            new ReglasVariacion2(),
            new ReglasVariacion3()
        );
        int indice = this.random.nextInt(variaciones.size());
        return variaciones.get(indice);
    }

    /**
     * Retorna la regla por defecto o base.
     */
    public ReglasDia crearReglaBase() {
        return new ReglasVariacion1();
    }
}