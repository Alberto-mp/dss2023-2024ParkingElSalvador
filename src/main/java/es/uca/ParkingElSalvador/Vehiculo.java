import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.Duration;


public class Vehiculo {
    private final String matricula;
    private LocalDateTime llegada;
    private LocalDateTime salida;
    private boolean pagado;
    private boolean tieneBono;

    public Vehiculo(String m){
        matricula = m;
        pagado = false;
        tieneBono = false; //Por defecto no tendran
    }

    // Metodos de control de hora de llegada y salida
    public void llega(){
        llegada = LocalDateTime.now();
    }

    public void sale(){
        salida = LocalDateTime.now();
    }

    public long duracion(){
        return Duration.between(llegada,salida).toMinutes();
    }

    public void pagarBono(){
        pagado = true;
    }

    // Metodos observadores
    public String matricula(){
        return matricula;
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

    public boolean poseeBono(){
        return tieneBono;
    }

    public boolean haPagado(){
        return pagado;
    }

    public String toString(){
        return "Matricula -> " + matricula;
    }


}
