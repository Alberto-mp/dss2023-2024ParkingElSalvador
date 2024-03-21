public class ParkingElSalvador {
    private final Parking parkES;
    private final Tarificacion tarifas;
    public ParkingElSalvador(Parking park, Tarificacion tari){
        parkES = park;
        tarifas = tari;
        parkES.setTarificacion(tari);   // definir este metodo
    }
    
    public String toString() {
        return parkES.toString()+tarifas.toString();
    }
}
