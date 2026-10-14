package modelo;

public enum TipoAspecto {
    ALIEN("assets/imagenes/ingresantes/alien.png"),
    ALIEN_PONELE("assets/imagenes/ingresantes/alien_ponele.png"),
    ASTRONAUTA("assets/imagenes/ingresantes/astronauta.png"),
    CABALLERO("assets/imagenes/ingresantes/caballero.png"),
    MAGO_CLAN_2("assets/imagenes/ingresantes/mago_clan2.png"),
    MAGO_CLAN_3("assets/imagenes/ingresantes/mago_clan_3.png"),
    MAGO_CLAN_4("assets/imagenes/ingresantes/mago_clan_4.png"),
    VIAJERO("assets/imagenes/ingresantes/viajero.png"),
    VIAJERO_POBRE("assets/imagenes/ingresantes/viajero_pobre.png"),
    VIKINGO("assets/imagenes/ingresantes/vikingo.png");

    private final String rutaImagen;

    TipoAspecto(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    public String getRutaImagen() {
        return this.rutaImagen;
    }
}