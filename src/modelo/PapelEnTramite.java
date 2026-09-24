package modelo;

public class PapelEnTramite extends Documento {
    private String firmalegal;

    public PapelEnTramite(String fechaVencimiento, int numId, String firmalegal, boolean trucho){
        super(fechaVencimiento, numId, trucho);
        if (firmalegal == null || firmalegal.isBlank()){
            throw new IllegalArgumentException("Firma legal no valida");
        }
        this.firmalegal = firmalegal;
    }

    public String getFirmaLegal(){
        return this.firmalegal;
    }

    public void mostrarDetalle(){
        
        System.out.println("======== PAPEL EN TRAMITE ========:");
        System.out.println("Firma Legal: "+ firmalegal);
        System.out.println("");
        
        super.mostrarDetalle();
    }

}
