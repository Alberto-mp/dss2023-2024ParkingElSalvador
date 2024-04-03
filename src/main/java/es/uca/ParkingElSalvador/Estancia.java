package es.uca.ParkingElSalvador;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Estancia {
    private LocalDateTime llegada;
    private LocalDateTime salida;
    private Vehiculo vehiculo;
    public Estancia(Vehiculo veh) {
        llegada = LocalDateTime.now();
        vehiculo = veh;
    }
    public void termina(){
        salida = LocalDateTime.now();
    }

    public int duracion(){
        return (int)Duration.between(llegada,LocalDateTime.now()).toMinutes();
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
}
