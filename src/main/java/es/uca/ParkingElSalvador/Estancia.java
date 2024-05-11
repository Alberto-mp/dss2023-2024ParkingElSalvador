package es.uca.ParkingElSalvador;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vehiculo_id")
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

    // Getter para la fecha de llegada
    public LocalDateTime getLlegada() {
        return llegada;
    }

    // Getter para la fecha de salida
    public LocalDateTime getSalida() {
        return salida;
    }

    // Getter para la fecha de fin de bono
    public LocalDateTime getFinBono() {
        return finBono;
    }

    // Getter para el estado de pago
    public boolean isPagado() {
        return pagado;
    }

    // Getter para el dinero pagado
    public double getDineroPagado() {
        return dineroPagado;
    }

    // Getter para saber si tiene bono
    public boolean isTieneBono() {
        return tieneBono;
    }

    // Getter para el vehículo asociado
    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    // Getter para el bono asociado
    public Bono getBono() {
        return bono;
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

}
