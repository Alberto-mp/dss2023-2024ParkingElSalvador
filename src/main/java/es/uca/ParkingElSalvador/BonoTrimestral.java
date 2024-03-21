public class BonoTrimestral extends Bono {
    private long pTrimestre;
    private int nTrimestres;

    public BonoTrimestral(int nTrimestres){
        pTrimestre = 0;
        this.nTrimestres = nTrimestres;
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
