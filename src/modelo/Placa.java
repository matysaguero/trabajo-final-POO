package modelo;

public class Placa extends Documento {
    private String clan;
    private int idPlaca;

    public Placa(String fechaVencimiento, int numId, String clan, int idPlaca, boolean trucho){
        super(fechaVencimiento, numId, trucho);
        if (clan == null || clan.isBlank()){
            throw new IllegalArgumentException("Clan no valido");
        }
        this.clan = clan;
        this.idPlaca = idPlaca;
    }

    public String getClan(){
        return this.clan;
    }

    public int getIdPlaca(){
        return this.idPlaca;
    }

    public void mostrarDetalle(){
        
        System.out.println("======== PLACA ========:");
        System.out.println("Clan: "+ clan);
        System.out.println("ID Placa: "+ idPlaca);
        System.out.println("");
        
        super.mostrarDetalle();
    }

}
