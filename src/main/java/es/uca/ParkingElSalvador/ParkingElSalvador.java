package es.uca.ParkingElSalvador;

public class ParkingElSalvador {
    private final Parking parkES;
    
    public ParkingElSalvador(Parking park, long estandar, long mes, long trimestre, long anno){
        parkES = park;
        parkES.precioEstandar(estandar);
        park.ponerPrecioBonos(mes,trimestre,anno);
    }
    
    public String toString() {
        return parkES.toString();
    }
}
