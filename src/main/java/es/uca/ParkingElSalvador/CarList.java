import java.util.HashMap;

public class CarList {
    private final HashMap<String, Vehiculo> vehiculos;
    public CarList(){
        vehiculos = new HashMap<>();
    }

    // Tratamiento de vehiculos
    public void meter(Vehiculo veh){
        vehiculos.put(veh.matricula(), veh);
    }
    public void sacar(Vehiculo veh){
        vehiculos.remove(veh.matricula(), veh);
    }


    // Metodos observadores
    public Vehiculo obtener(String matricula){
        return vehiculos.get(matricula);
    }
    public int numCoches(){
        return vehiculos.size();
    }


}
