package es.uca.ParkingElSalvador;

public class BonoMensual implements Bono {
    public static double pMes = 0;
    private int nMeses;

    public BonoMensual(int nMeses){
        this.nMeses = nMeses;
    }


    public static double precioMensual() {
        return pMes;
    }

    @Override
    public void ponerPrecioBono(double precioM){
        pMes = precioM;
    }
    @Override
    public double precioBono() {
        return nMeses*pMes;
    }
    @Override
    public String tipoBono(){
        return "El bono es mensual";
    }

}
