import java.util.HashMap;

public class CarList {
    private final HashMap<String, Vehiculo> vehiculos;
    public CarList(){
        vehiculos = new HashMap<>();
    }

    // Tratamiento de vehiculos
    public void meter(String matricula, Vehiculo veh){
        vehiculos.put(matricula, veh);
    }
    public void sacar(String matricula, Vehiculo veh){
        vehiculos.remove(matricula, veh);
    }


    // Metodos observadores
    public Vehiculo obtener(String matricula){
        return vehiculos.get(matricula);
    }
    public int numCoches(){
        return vehiculos.size();
    }


}
