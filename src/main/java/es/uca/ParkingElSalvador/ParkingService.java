package es.uca.ParkingElSalvador;

import java.math.BigDecimal;
import javax.persistence.Service;

@Service
public class ParkingService {
    private Parking p;
    private Barrera barrera;
    private QRservice qr;
    private Estandar tarifa;
    private CarService vehiculos;
    private EstanciasService libro;
    private BonoService bonos;
    private Cajero caja;
    
    public ParkingService(Parking p){
        this.p = p;
        barrera = new Barrera();
        qr = new QRservice();
        tarifa = new Estandar(); 
        vehiculos = new CarService();
        libro = new EstanciasService();
        bonos = new BonoService();
        caja = new Cajero();
    }

    public void precioEstandar(long min){
        tarifa.ponerPrecioAlMinuto(min);;
    }

    public void ponerPrecioBonos(double mes, double tri, double anno){
        BonoMensual.setPrecio(new BigDecimal(mes));
        BonoTrimestral.setPrecio(new BigDecimal(tri));
        BonoAnual.setPrecio(new BigDecimal(anno));
    }

    public void setDirectorioQR(String d){
        qr.setDirectorio(d);
    }

    // Getters
    public Parking getP() {
        return p;
    }
    
    public Barrera getBarrera() {
        return barrera;
    }
    
    public QRservice getQr() {
        return qr;
    }
    
    public Estandar getTarifa() {
        return tarifa;
    }
    
    public CarService getVehiculos() {
        return vehiculos;
    }
    
    public EstanciasService getLibro() {
        return libro;
    }
    
    public BonoService getBonos() {
        return bonos;
    }
    
    public Cajero getCaja() {
        return caja;
    }
    
    // Setters
    public void setP(Parking p) {
        this.p = p;
    }
    
    public void setBarrera(Barrera barrera) {
        this.barrera = barrera;
    }
    
    public void setQr(QRservice qr) {
        this.qr = qr;
    }
    
    public void setTarifa(Estandar tarifa) {
        this.tarifa = tarifa;
    }
    
    public void setVehiculos(CarService vehiculos) {
        this.vehiculos = vehiculos;
    }
    
    public void setLibro(EstanciasService libro) {
        this.libro = libro;
    }
    
    public void setBonos(BonoService bonos) {
        this.bonos = bonos;
    }
    
    public void setCaja(Cajero caja) {
        this.caja = caja;
    }


    public void entrada() throws Exception {
        // Se lee la matricula del qr generado cuando llega el ultimo coche
        String matricula = qr.leerCodigoQR();
        Vehiculo vehiculo = new Vehiculo(matricula);
        // Comprobamos que haya espacio
        if(p.plazasDisponibles > 0){
            // Abrimos la barrera en caso de estar cerrada
            if(!barrera.estaAbierta())
                barrera.abrirBarrera();
            // Generamos el ticket y le damos acceso
            qr.generarCodigoQR(matricula);
            vehiculos.save(vehiculo);
            barrera.cerrarBarrera();
            p.plazasDisponibles--;
            p.plazasOcupadas++;

        }
    }

    public void salida() throws Exception{
        String matricula = qr.leerCodigoQR();
        Vehiculo vehiculo = vehiculos.getVehiculo(matricula);
        if(vehiculo.estancia().haPagado() || (vehiculo.estancia().poseeBono() && vehiculo.estancia().bonoValido())){
            vehiculo.sale();
            barrera.abrirBarrera();
            // Sale del parking
            barrera.cerrarBarrera();
            vehiculos.delete(vehiculo);
            libro.save(vehiculo);
            p.plazasDisponibles++;
            p.plazasOcupadas--;

        }
    }

    // En caso de no funcionar el lector de qr

    public void entrada(String matricula) {
        Vehiculo vehiculo = new Vehiculo(matricula);
        // Comprobamos que haya espacio
        if(p.plazasDisponibles > 0){
            // Abrimos la barrera en caso de estar cerrada
            if(!barrera.estaAbierta())
                barrera.abrirBarrera();
            // Generamos el ticket y le damos acceso
            qr.generarCodigoQR(matricula);
            vehiculos.save(vehiculo);
            libro.save(vehiculo);
            barrera.cerrarBarrera();
            p.plazasDisponibles--;
            p.plazasOcupadas++;

        }
    }

    public void salida(String matricula){
        Vehiculo vehiculo = vehiculos.getVehiculo(matricula);
        if(vehiculo.estancia().haPagado() || (vehiculo.estancia().poseeBono() && vehiculo.estancia().bonoValido())){
            vehiculo.sale();
            barrera.abrirBarrera();
            // Sale del parking
            barrera.cerrarBarrera();
            vehiculos.delete(vehiculo);
            p.plazasDisponibles++;
            p.plazasOcupadas--;

        }
    }

    // Operaciones de pago de tarifa estándar y bonos
    public void vehiculoPagaEstandar(BigDecimal entregado, String mat, char F) {
        Vehiculo v = vehiculos.getVehiculo(mat);
        vehiculos.delete(v);
        PagoEstandar pEstandar = new PagoEstandar(caja,v,tarifa.precioMinuto());
        pEstandar.pagar(entregado, F);
        vehiculos.save(v);
    }

    public void vehiculoPagaBonoMensual(BigDecimal entregado, int nMeses, String mat, char F){
        Vehiculo v = vehiculos.getVehiculo(mat);
        vehiculos.delete(v);
        PagoBono pBono = new PagoBono(v);
        pBono.comprarBonoMensual(entregado,nMeses,F);
        vehiculos.save(v);
    }

    public void vehiculoPagaBonoTrimestral(BigDecimal entregado, int nTrimestres, String mat, char F){
        Vehiculo v = vehiculos.getVehiculo(mat);
        vehiculos.delete(v);
        PagoBono pBono = new PagoBono(v);
        pBono.comprarBonoTrimestral(entregado,nTrimestres,F);
        vehiculos.save(v);
    }
    
    public void vehiculoPagaBonoAnual(BigDecimal entregado,int nAnnos, String mat, char F){
        Vehiculo v = vehiculos.getVehiculo(mat);
        vehiculos.delete(v);
        PagoBono pBono = new PagoBono(v);
        pBono.comprarBonoAnual(entregado,nAnnos,F);
        vehiculos.save(v);
    }
}
