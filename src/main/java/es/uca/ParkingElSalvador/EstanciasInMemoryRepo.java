package es.uca.ParkingElSalvador;

import java.util.ArrayList;
import java.util.List;

public class EstanciasInMemoryRepo implements EstanciasRepository {
    private final List<Estancia> registro;

    public EstanciasInMemoryRepo() {
        registro = new ArrayList<>();
    }

    public List<Estancia> registro() {
        return registro;
    }

    public void almacenar(Vehiculo vehiculo) {
        registro.add(vehiculo.estancia());
    }

    public boolean haestadoCoche(String matricula) {
        for (Estancia estancia : registro) {
            if (estancia.vehiculo().matricula().equals(matricula)) {
                return true;
            }
        }
        return false;
    }

    public List<Estancia> estancias(String matricula) {
        List<Estancia> apariciones = new ArrayList<>();
        for (Estancia estancia : registro) {
            if (estancia.vehiculo().matricula().equals(matricula)) {
                apariciones.add(estancia);
            }
        }
        return apariciones;
    }

    public List<Estancia> estancias() {
        return new ArrayList<>(registro);
    }

    public long numEstancias() {
        return registro.size();
    }
}
