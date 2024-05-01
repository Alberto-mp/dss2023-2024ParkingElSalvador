package es.uca.ParkingElSalvador;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;

import org.springframework.stereotype.Service;

@Service
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

    public String toString(){
        return "Informe creado el "+fechaInforme()+" de "+parking.getNombre();
    }

    public double ingresoDiario(){
        double ingresoDiario = 0;
        EstanciasService libro = parking.getLibro();
        int hoy = LocalDateTime.now().getDayOfYear();
        for(int i = 0; i < libro.numEstancias(); i++){
            if(libro.getAllEstancias().get(i).horaLlegadaLT().getDayOfYear() == hoy)
                ingresoDiario += parking.getLibro().getAllEstancias().get(i).vehiculo().estancia().dineroPagado();
        }
        return ingresoDiario;
    }

    
    public double ingresoSemanal(){
        double ingresoSemanal = 0;
        EstanciasService libro = parking.getLibro();
        int semana = LocalDateTime.now().get(WeekFields.ISO.weekOfWeekBasedYear());
        for(int i = 0; i < libro.numEstancias(); i++){
            if(libro.getAllEstancias().get(i).horaLlegadaLT().get(WeekFields.ISO.weekOfWeekBasedYear()) == semana)
            ingresoSemanal += parking.getLibro().getAllEstancias().get(i).vehiculo().estancia().dineroPagado();
        }
        return ingresoSemanal;
    }


    public double ingresoMensual(){
        double ingresoMensual = 0;
        EstanciasService libro = parking.getLibro();
        int mes = LocalDateTime.now().getMonthValue();
        for(int i = 0; i < libro.numEstancias(); i++){
            if(libro.getAllEstancias().get(i).horaLlegadaLT().getMonthValue() == mes)
            ingresoMensual += parking.getLibro().getAllEstancias().get(i).vehiculo().estancia().dineroPagado();
        }
        return ingresoMensual;
    }

   
}
