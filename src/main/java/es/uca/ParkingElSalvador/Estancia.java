package es.uca.ParkingElSalvador;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Estancia {
    private LocalDateTime llegada;
    private LocalDateTime salida;
    private LocalDateTime finBono;
    private boolean pagado;
    private double dineroPagado;
    private boolean tieneBono;
    private Vehiculo vehiculo;

    public Estancia(Vehiculo veh) {
        llegada = LocalDateTime.now();
        vehiculo = veh;
        pagado = false;
        tieneBono = false; //Por defecto no tendran
        dineroPagado = 0;
    }

    public void termina(){
        salida = LocalDateTime.now();
    }

    public int duracion(){
        return (int)Duration.between(llegada,LocalDateTime.now()).toMinutes();
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

    public String horaLlegada(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // Formatea el LocalDateTime en una cadena usando el formateador
        String fechaHoraString = llegada.format(formatter);
        return fechaHoraString;
    }

    public String horaSalida(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // Formatea el LocalDateTime en una cadena usando el formateador
        String fechaHoraString = salida.format(formatter);
        return fechaHoraString;
    }

    public LocalDateTime horaLlegadaLT(){
        return llegada;
    }

    public LocalDateTime horaSalidaLT(){
        return salida;
    }

    public Vehiculo vehiculo(){
        return vehiculo;
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
}
