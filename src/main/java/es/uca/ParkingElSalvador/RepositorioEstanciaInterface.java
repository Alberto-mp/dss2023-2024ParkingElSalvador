package es.uca.ParkingElSalvador;

import java.util.Vector;

public interface RepositorioEstanciaInterface {
    public void almacenar(Vehiculo vehiculo);
    public boolean haestadoCoche(String matricula);
    public Vector<Estancia> estancias(String matricula);
}
