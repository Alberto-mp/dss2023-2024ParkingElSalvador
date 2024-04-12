package es.uca.ParkingElSalvador;

import java.util.List;

public interface BonoRepository {
    public void meter(Bono b);
    public void sacar(Bono b);
    public Bono obtener(Estancia e);
    public List<Bono> bonos(String matricula);
    public List<Bono> getAllBonos();
}
