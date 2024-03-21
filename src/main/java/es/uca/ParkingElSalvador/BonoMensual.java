public class BonoMensual extends Bono {
    private long pMes;
    private int nMeses;

    public BonoMensual(int nMeses){
        pMes = 0;
        this.nMeses = nMeses;
    }

    public void ponerPrecioBono(long precioM){
        pMes = precioM;
    }

    public double precioBono() {
        return nMeses*pMes;
    }

    public String tipoBono(){
        return "El bono es mensual";
    }

}
