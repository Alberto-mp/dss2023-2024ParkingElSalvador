package es.uca.ParkingElSalvador;

import java.util.Vector;

public class RepositorioEstancias implements RepositorioEstanciaInterface {
    private final Vector<Estancia> registro;
    public RepositorioEstancias(){
        registro = new Vector<Estancia>();
    }
    public Vector<Estancia> registro(){
        return registro;
    }
    public void almacenar(Vehiculo vehiculo){
        registro.add(vehiculo.estancia());
    }
    public boolean haestadoCoche(String matricula){
        boolean haEstado = false;
        for(int i = 0; i < registro.size() && !haEstado; i++){
            if(registro.get(i).vehiculo().matricula() == matricula)
                haEstado = true;
        }
        return haEstado;
    }
    public Vector<Estancia> estancias(String matricula){
        Vector<Estancia> apariciones = new Vector<>();
        for(int i = 0; i < registro.size(); i++){
            if(registro.get(i).vehiculo().matricula() == matricula)
                apariciones.add(registro.get(i));
        }
        return apariciones;
    }
}
