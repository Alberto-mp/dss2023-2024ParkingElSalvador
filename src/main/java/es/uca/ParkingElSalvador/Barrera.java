package es.uca.ParkingElSalvador;

public class Barrera {
    private boolean estado; // Tomaremos True como abierta y false como cerrada
  
    public Barrera(){
        estado = false;
    }

    // Metodos modificadores de la barrera
    public void abrirBarrera(){
        if(!estado)
            estado = true;
    }
    
    public void cerrarBarrera(){
        if(estado)
            estado = false;
    }

    // Observador de la barrera
    public boolean estaAbierta(){
        return estado;
    }
    
}
