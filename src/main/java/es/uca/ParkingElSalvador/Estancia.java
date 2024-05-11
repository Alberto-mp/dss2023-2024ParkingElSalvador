package es.uca.ParkingElSalvador;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
public class Estancia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime llegada;
    private LocalDateTime salida;
    private LocalDateTime finBono;
    private boolean pagado;
    private double dineroPagado;
    private boolean tieneBono;
    
    @OneToOne(fetch = FetchType.EAGER)
    private Vehiculo vehiculo;

    @OneToOne
    private Bono bono;

    public Estancia(Vehiculo veh) {
        llegada = LocalDateTime.now();
        salida = null;
        vehiculo = veh;
        pagado = false;
        tieneBono = false; //Por defecto no tendrán
        dineroPagado = 0;
        bono = null;
    }

    public Estancia() {
        llegada = LocalDateTime.now();
        salida = null;
        vehiculo = null;
        pagado = false;
        tieneBono = false; //Por defecto no tendrán
        dineroPagado = 0;
        bono = null;
    }

    public void setVehiculo(Vehiculo v){
        vehiculo = v;
    }

    public String toString() {
        return "Estancia del vehiculo "+vehiculo.getMatricula()+" Llego "+horaLlegada()+". Salio "+horaSalida();
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
        if(salida!=null){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            // Formatea el LocalDateTime en una cadena usando el formateador
            String fechaHoraString = salida.format(formatter);
            return fechaHoraString;
        }
        else
            return "--Vehiculo sigue en el parking--";
        
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

    public Bono getBono(){
        return bono;
    }

    public String bonoTipo() {
        if(bono!=null)
            return bono.tipoBono();
        else
            return "No posee";
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
