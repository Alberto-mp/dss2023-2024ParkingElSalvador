package es.uca.ParkingElSalvador;

public class BonoTrimestral extends Bono {
    public static double pTrimestre = 0;
    private int nTrimestres;

    public BonoTrimestral(int nTrimestres){
        this.nTrimestres = nTrimestres;
    }

    public static double precioTrimestral() {
        return pTrimestre;
    }

    public void ponerPrecioBono(double precioT){
        pTrimestre = precioT;
    }

    public double precioBono() {
        return nTrimestres*pTrimestre;
    }

    public String tipoBono(){
        return "El bono es trimestral";
    }

}
