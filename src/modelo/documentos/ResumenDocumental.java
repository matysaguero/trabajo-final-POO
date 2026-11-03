package modelo.documentos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public final class ResumenDocumental {

    private final Set<RequisitoDocumental> acreditaciones = EnumSet.noneOf(RequisitoDocumental.class);
    private final List<String> numerosDni = new ArrayList<>();
    private final List<String> dniEnPermisos = new ArrayList<>();
    private final List<String> razasDeclaradas = new ArrayList<>();
    private final List<LocalDate> nacimientosDeclarados = new ArrayList<>();

    public void acreditar(RequisitoDocumental requisito) {
        acreditaciones.add(Objects.requireNonNull(requisito));
    }

    public boolean acredita(RequisitoDocumental requisito) {
        return acreditaciones.contains(requisito);
    }

    public void registrarNumeroDni(String numero) {
        numerosDni.add(Objects.requireNonNull(numero));
    }

    public void registrarDniEnPermiso(String numero) {
        dniEnPermisos.add(Objects.requireNonNull(numero));
    }

    public void registrarRaza(String raza) {
        razasDeclaradas.add(Objects.requireNonNull(raza));
    }

    public void registrarNacimiento(LocalDate fecha) {
        nacimientosDeclarados.add(Objects.requireNonNull(fecha));
    }

    public List<String> numerosDni() {
        return List.copyOf(numerosDni);
    }

    public List<String> dniEnPermisos() {
        return List.copyOf(dniEnPermisos);
    }

    public List<String> razasDeclaradas() {
        return List.copyOf(razasDeclaradas);
    }

    public List<LocalDate> nacimientosDeclarados() {
        return List.copyOf(nacimientosDeclarados);
    }

/* 
Propósito: reunir los datos que los documentos aportan, 
de manera que cada política pueda realizar sus comparaciones.    

ResumenDocumental no necesita saber cuál es la clase concreta de cada documento. 
Solo almacena información que recibe mediante 
métodos como registrarRaza() y registrarNumeroDni().

Además, los métodos que devuelven listas utilizan List.copyOf() 
para que una política no pueda modificar directamente las 
colecciones internas del resumen.
*/
 
    
}