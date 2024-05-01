package es.uca.ParkingElSalvador;

public class ParkingElSalvador {
    private final Parking parkES;
    private final ParkingService p;
    
    public ParkingElSalvador(Parking park){
        parkES = park;
        p = new ParkingService(park);
    }

    public ParkingService getParkingService(){
        return p;
    }

    public String toString() {
        return parkES.toString();
    }
}
