import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Informe {
    private LocalDateTime creacion;
    private long ingresoDiario;
    private long ingresoSemanal;
    private long ingresoMensual; 

    public Informe(){
        creacion = LocalDateTime.now();
        ingresoDiario = 0;
        ingresoSemanal = 0;
        ingresoMensual = 0;
    }

    // Metodos observadores proporcionados por el informe
    public String fechaInforme(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // Formatea el LocalDateTime en una cadena usando el formateador
        String fechaHoraString = creacion.format(formatter);
        return fechaHoraString;
    }

    public long ingresoDiario(){
        return ingresoDiario;
    }

    public long ingresoSemanal(){
        return ingresoSemanal;
    }

    public long ingresoMensual(){
        return ingresoMensual;
    }
}
