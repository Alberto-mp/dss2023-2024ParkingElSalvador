public class BonoTrimestral extends Bono {
    public static long pTrimestre = 0;
    private int nTrimestres;

    public BonoTrimestral(int nTrimestres){
        this.nTrimestres = nTrimestres;
    }

    public static long precioTrimestral() {
        return pTrimestre;
    }

    public void ponerPrecioBono(long precioT){
        pTrimestre = precioT;
    }

    public double precioBono() {
        return nTrimestres*pTrimestre;
    }

    public String tipoBono(){
        return "El bono es trimestral";
    }

}
