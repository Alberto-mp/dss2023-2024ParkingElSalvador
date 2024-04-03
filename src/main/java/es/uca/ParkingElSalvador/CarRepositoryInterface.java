package es.uca.ParkingElSalvador;

public interface CarRepositoryInterface {
    public void meter(Vehiculo v);
    public void sacar(Vehiculo v);
    public Vehiculo obtener(String matricula);
    public int numCoches();
} 
