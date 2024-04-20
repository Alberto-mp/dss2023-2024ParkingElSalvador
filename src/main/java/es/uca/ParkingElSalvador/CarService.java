package es.uca.ParkingElSalvador;


public class CarService {
    private CarRepository vehiculos;
    public CarService(){
        vehiculos = new CarRepositoryInMemoryRepo();
    }

    public void save(Vehiculo v){
        vehiculos.meter(v);
    }

    public void delete(Vehiculo v){
        vehiculos.sacar(v);
    }

    public Vehiculo getVehiculo(String matricula){
        return vehiculos.obtener(matricula);
    }

    public int getNumCoches(){
        return vehiculos.numCoches();
    }
} 


