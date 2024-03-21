public class BonoAnual extends Bono {
    public static long pAnual = 0;
    private int nAnos;

    public BonoAnual(int nAnos){
        this.nAnos = nAnos;
    }

    public static long precioAnual() {
        return pAnual;
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
