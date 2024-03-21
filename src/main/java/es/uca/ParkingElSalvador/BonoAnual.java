public class BonoAnual extends Bono {
    private long pAnual;
    private int nAnos;

    public BonoAnual(int nAnos){
        pAnual = 0;
        this.nAnos = nAnos;
    }

    public void ponerPrecioBono(long precioA){
        pAnual = precioA;
    }

    public double precioBono() {
        return nAnos*pAnual;
    }

    public String tipoBono(){
        return "El bono es anual";
    }

}
