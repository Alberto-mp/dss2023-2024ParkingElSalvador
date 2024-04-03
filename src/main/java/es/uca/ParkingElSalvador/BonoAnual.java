package es.uca.ParkingElSalvador;

public class BonoAnual extends Bono {
    public static double pAnual = 0;
    private int nAnos;

    public BonoAnual(int nAnos){
        this.nAnos = nAnos;
    }

    public static double precioAnual() {
        return pAnual;
    }

    public void ponerPrecioBono(double precioA){
        pAnual = precioA;
    }

    public double precioBono() {
        return (double)nAnos*pAnual;
    }

    public String tipoBono(){
        return "El bono es anual";
    }

}
