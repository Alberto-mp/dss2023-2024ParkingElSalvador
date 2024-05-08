package es.uca.ParkingElSalvador;

import java.util.List;

public interface EstanciasRepository {
    public void almacenar(Vehiculo vehiculo);
    public boolean haestadoCoche(String matricula);
    public List<Estancia> estancias(String matricula);
    public List<Estancia> estancias();
    public long numEstancias();
}
