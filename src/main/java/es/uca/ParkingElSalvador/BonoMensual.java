package es.uca.ParkingElSalvador;

public class BonoMensual extends Bono {
    public static double pMes = 0;
    private int nMeses;

    public BonoMensual(int nMeses){
        this.nMeses = nMeses;
    }

    public static double precioMensual() {
        return pMes;
    }

    public void ponerPrecioBono(double precioM){
        pMes = precioM;
    }

    public double precioBono() {
        return nMeses*pMes;
    }

    public String tipoBono(){
        return "El bono es mensual";
    }

}
