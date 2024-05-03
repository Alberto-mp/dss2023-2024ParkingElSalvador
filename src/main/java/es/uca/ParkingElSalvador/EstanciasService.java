package es.uca.ParkingElSalvador;

import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstanciasService {
    private EstanciasRepository estancias;
    @Autowired
    public EstanciasService(EstanciasRepository e){
        estancias = e;
    }
    public void save(Vehiculo v){
        estancias.almacenar(v);
    }
    public boolean esta(String matricula){
        return estancias.haestadoCoche(matricula);
    }
    public Vector<Estancia> getEstancias(String matricula){
        return estancias.estancias(matricula);
    }
    public Vector<Estancia> getAllEstancias(){
        return estancias.estancias();
    }
    public int numEstancias(){
        return estancias.numEstancias();
    }

}

