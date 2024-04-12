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
    public Bono obtener(Estancia e){
        
    }

    @Override
    public List<Bono> bonos(String matricula){
        
    }

    @Override
    public List<Bono> getAllBonos() {

    }
}
