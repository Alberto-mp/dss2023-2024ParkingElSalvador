package es.uca.ParkingElSalvador;

import java.util.List;

public class BonoService {
    private BonoRepository bonos;
    public BonoService(){
        bonos = new BonoInMemoryRepo();
    }

    public void save(Bono b){
        bonos.meter(b);
    }

    public void delete(Bono b){
        bonos.sacar(b);
    }

    public List<Bono> getBonos(String matricula){
        return bonos.bonos(matricula);
    }

    public List<Bono> getBonos(){
        return bonos.getAllBonos();
    }
} 


