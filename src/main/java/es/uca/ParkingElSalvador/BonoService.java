package es.uca.ParkingElSalvador;

import java.util.List;
import java.util.Vector;

public class BonoService implements BonoRepository{
    private Vector<Bono> bonos;

    
    public BonoService() {
        bonos = new Vector<Bono>();
    }
    
    @Override
    public void meter(Bono b){
        bonos.add(b);
    }

    @Override
    public void sacar(Bono b){
        bonos.remove(b);
    }

    @Override
    public List<Bono> bonos(String matricula){
        List<Bono> lista = new List<Bono>();
        for(Bono b : bonos){
            if(b.getVehiculo().matricula() == matricula)
               lista.add(b);
        }
        return lista;
    }

    @Override
    public List<Bono> getAllBonos() {
        return bonos;
    }
}
