package es.uca.ParkingElSalvador;

import java.time.LocalDateTime;

public class Vehiculo {
    private final String matricula;
    private LocalDateTime finBono;
    private boolean pagado;
    private double dineroPagado;
    private boolean tieneBono;
    private Estancia estancia;

    public Vehiculo(String m){
        matricula = m;
        pagado = false;
        tieneBono = false; //Por defecto no tendran
        dineroPagado = 0;
        estancia = new Estancia(this);
    }

    public void sale(){
        estancia.termina();
    }

    public void compraBono(){
        tieneBono = true;
    }

    public void pagarEstandar(){
        pagado = true;
    }

    public void setFinBono(LocalDateTime f){
        finBono = f;
    }

    public void setDineroPagado(double d){
        dineroPagado = d;
    }

    public LocalDateTime fechaFinBono(){
        return finBono;
    }

    // Metodos observadores
    public String matricula(){
        return matricula;
    }

    public Estancia estancia(){
        return estancia;
    }

    public boolean bonoValido(){
        if(tieneBono){
            if(LocalDateTime.now().isBefore(finBono))
                return true;
            else
                return false;
        }

        else
            return false;

    }

    public boolean poseeBono(){
        return tieneBono;
    }

    public boolean haPagado(){
        return pagado;
    }

    public double dineroPagado(){
        return dineroPagado;
    }

    public String toString(){
        return "Matricula -> " + matricula;
    }

}
