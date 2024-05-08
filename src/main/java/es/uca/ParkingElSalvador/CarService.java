package es.uca.ParkingElSalvador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService {
    private CarRepository vehiculos;
    @Autowired
    public CarService(CarRepository v ){
        vehiculos = v;
    }

    public CarService(){
        vehiculos = null;
    }

    public void setCarRepository(CarRepository v){
        vehiculos = v;
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


