package modelo;

public class DNI extends Documento {
    private String huella;
    private String lugarNacimiento;
    private String sexo;
    private String domicilio;


public DNI(String fechaVencimiento, int numId, boolean trucho, String huella, String lugarNacimiento, String sexo, String domicilio){
super(fechaVencimiento, numId, trucho);
if (huella == null || huella.isBlank()) {
throw new IllegalArgumentException("huella no puede estar vacio ni ser nulo");    
}
if (lugarNacimiento == null || lugarNacimiento.isBlank()) {
throw new IllegalArgumentException("lugarNacimiento no puede estar vacio ni ser nulo");
}
if (sexo == null || sexo.isBlank()) {
throw new IllegalArgumentException("sexo no puede estar vacio ni ser nulo");
}
if (domicilio == null || domicilio.isBlank()) {
throw new IllegalArgumentException("domicilio no puede estar vacio ni ser nulo");
}

this.huella = huella;
this.lugarNacimiento = lugarNacimiento;
this.sexo = sexo;
this.domicilio = domicilio;
}

public String getHuella(){
    return this.huella;
}

public String getLugarNacimiento(){
    return this.lugarNacimiento;
}

public String getSexo(){
    return this.sexo;
}

public String getDomicilio(){
    return this.domicilio;
}

}