package es.uca.ParkingElSalvador;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;

public class Informe {
    private Parking parking;
    private LocalDateTime creacion;


    public Informe(Parking p){
        parking = p;
        creacion = LocalDateTime.now();
    }

    // Metodos observadores proporcionados por el informe
    public String fechaInforme(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // Formatea el LocalDateTime en una cadena usando el formateador
        String fechaHoraString = creacion.format(formatter);
        return fechaHoraString;
    }
    public LocalDateTime fechaInformeLT(){
        return creacion;
    }

    public double ingresoDiario(){
        double ingresoDiario = 0;
        EstanciasService libro = parking.getLibro();
        int hoy = LocalDateTime.now().getDayOfYear();
        for(int i = 0; i < libro.registro().size(); i++){
            if(libro.registro().get(i).horaLlegadaLT().getDayOfYear() == hoy)
                ingresoDiario += parking.getLibro().registro().get(i).vehiculo().estancia().dineroPagado();
        }
        return ingresoDiario;
    }

    
    public double ingresoSemanal(){
        double ingresoSemanal = 0;
        EstanciasService libro = parking.getLibro();
        int semana = LocalDateTime.now().get(WeekFields.ISO.weekOfWeekBasedYear());
        for(int i = 0; i < libro.registro().size(); i++){
            if(libro.registro().get(i).horaLlegadaLT().get(WeekFields.ISO.weekOfWeekBasedYear()) == semana)
            ingresoSemanal += parking.getLibro().registro().get(i).vehiculo().estancia().dineroPagado();
        }
        return ingresoSemanal;
    }


    public double ingresoMensual(){
        double ingresoMensual = 0;
        EstanciasService libro = parking.getLibro();
        int mes = LocalDateTime.now().getMonthValue();
        for(int i = 0; i < libro.registro().size(); i++){
            if(libro.registro().get(i).horaLlegadaLT().getMonthValue() == mes)
            ingresoMensual += parking.getLibro().registro().get(i).vehiculo().estancia().dineroPagado();
        }
        return ingresoMensual;
    }

   
}
