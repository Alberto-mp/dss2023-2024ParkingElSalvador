package es.uca.ParkingElSalvador;

import java.util.Vector;

public interface EstanciasRepository {
    public void almacenar(Vehiculo vehiculo);
    public boolean haestadoCoche(String matricula);
    public Vector<Estancia> estancias(String matricula);
}
