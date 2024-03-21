import java.io.IOException;

import javax.naming.NameAlreadyBoundException;
import javax.naming.NameNotFoundException;

public class Parking {
    private final String nombre;
    private final String direccion_postal;
    private final int capacidadTotal;
    private int plazasDisponibles;
    private int plazasOcupadas;
    private Barrera barrera;
    private QRservice qr;
    private Estandar tarifa;
    private CarList vehiculos;
    private CarList libro;
    private Informe informeActual;
    private HashMap<LocalDateTime,Informe> informes;

    public Parking(String n, String d, int capT){
        nombre = n;
        direccion_postal = d;
        capacidadTotal = capT;
        plazasDisponibles = capT; 
        plazasOcupadas = 0;
        barrera = new Barrera();
        qr = new QRservice();
        tarifa = new Estandar(); 
        vehiculos = new CarList();
        libro = new CarList();
        informeActual = new Informe();
        informes = new HashMap<>();
    }

    public void precioEstandar(long min){
        tarifa.ponerPrecioAlMinuto(min);;
    }

    public void ponerPrecioBonos(long mes, long tri, long anno){
        BonoMensual.pMes = mes;
        BonoTrimestral.pTrimestre = tri; 
        BonoAnual.pAnual = anno;
    }


    // Observadores
    public String getNombre() {
        return nombre;
    }

    public String getDireccionPostal() {
        return direccion_postal;
    }

    public int getCapacidadTotal() {
        return capacidadTotal;
    }


    public int getPlazasDisponibles() {
        return plazasDisponibles;
    }

    public int getPlazasOcupadas() {
        return plazasOcupadas;
    }

    public Barrera getBarrera() {
        return barrera;
    }

    public QRservice getQR() {
        return qr;
    }

    public CarList getVehiculos() {
        return vehiculos;
    }

    public CarList getLibro() {
        return libro;
    }

    public Informe getInformeActual() {
        return informeActual;
    }

    public void entrada() throws IOException, NameNotFoundException {
        // Se lee la matricula del qr generado cuando llega el ultimo coche
        String matricula = qr.leerCodigoQR();
        Vehiculo vehiculo = new Vehiculo(matricula);
        // Comprobamos que haya espacio
        if(plazasDisponibles > 0){
            // Abrimos la barrera en caso de estar cerrada
            if(!barrera.estaAbierta())
                barrera.abrirBarrera();
            // Generamos el ticket y le damos acceso
            qr.generarCodigoQR(matricula);
            vehiculos.meter(vehiculo);
            libro.meter(vehiculo);
            vehiculo.llega();
            barrera.cerrarBarrera();
            plazasDisponibles--;
            plazasOcupadas++;

        }
    }

    public void salida() throws IOException, NameNotFoundException{
        String matricula = qr.leerCodigoQR();
        Vehiculo vehiculo = vehiculos.obtener(matricula);
        if(vehiculo.haPagado() || (vehiculo.poseeBono() && vehiculo.bonoValido())){
            vehiculo.sale();
            barrera.abrirBarrera();
            // Sale del parking
            barrera.cerrarBarrera();
            vehiculos.sacar(vehiculo);
            plazasDisponibles++;
            plazasOcupadas--;

        }
    }

    // Operaciones de pago de tarifa estándar y bonos
    public void vehiculoPagaEstandar(){
        pagoEstandar pEstandar = new pagoEstandar(vehiculos,tarifa);
        long cantidad = pEstandar.pagar();
        LocalDateTime fechaInf = informeActual.fechaInforme().withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime hoy = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        if(fechaInf.isEqual(hoy))
            informeActual.ingresoDiario(cantidad);
        else{
            if(fechaInf.get(WeekFields.ISO.weekOfWeekBasedYear()) == hoy.get(WeekFields.ISO.weekOfWeekBasedYear()))
                informeActual.ingresoSemanal(cantidad);
            else{
                if(fechaInf.getMonthValue() == hoy.getMonthValue())
                    informeActual.ingresoMensual(cant);
            }
        }
    }

    public void vehiculoPagaBonoMensual(int nMeses){
        pagoBono pBono = new pagoBono(vehiculos);
        long cantidad = pBono.comprarBonoMensual(nMeses);
        LocalDateTime fechaInf = informeActual.fechaInforme().withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime hoy = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        if(fechaInf.isEqual(hoy))
            informeActual.ingresoDiario(cantidad);
        else{
            if(fechaInf.get(WeekFields.ISO.weekOfWeekBasedYear()) == hoy.get(WeekFields.ISO.weekOfWeekBasedYear()))
                informeActual.ingresoSemanal(cantidad);
            else{
                if(fechaInf.getMonthValue() == hoy.getMonthValue())
                    informeActual.ingresoMensual(cantidad);
            }
        }
    }

    public void vehiculoPagaBonoTrimestral(int nTrimestres){
        pagoBono pBono = new pagoBono(vehiculos);
        long cantidad = pBono.comprarBonoTrimestral(nTrimestres);
        LocalDateTime fechaInf = informeActual.fechaInforme().withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime hoy = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        if(fechaInf.isEqual(hoy))
            informeActual.ingresoDiario(cantidad);
        else{
            if(fechaInf.get(WeekFields.ISO.weekOfWeekBasedYear()) == hoy.get(WeekFields.ISO.weekOfWeekBasedYear()))
                informeActual.ingresoSemanal(cantidad);
            else{
                if(fechaInf.getMonthValue() == hoy.getMonthValue())
                    informeActual.ingresoMensual(cantidad);
            }
        }
    }
    
    public void vehiculoPagaBonoAnual(int nAnnos){
        pagoBono pBono = new pagoBono(vehiculos);
        long cantidad = pBono.comprarBonoAnual(nAnnos);
        LocalDateTime fechaInf = informeActual.fechaInforme().withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime hoy = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        if(fechaInf.isEqual(hoy))
            informeActual.ingresoDiario(cantidad);
        else{
            if(fechaInf.get(WeekFields.ISO.weekOfWeekBasedYear()) == hoy.get(WeekFields.ISO.weekOfWeekBasedYear()))
                informeActual.ingresoSemanal(cantidad);
            else{
                if(fechaInf.getMonthValue() == hoy.getMonthValue())
                    informeActual.ingresoMensual(cantidad);
            }
        }
    }

}
