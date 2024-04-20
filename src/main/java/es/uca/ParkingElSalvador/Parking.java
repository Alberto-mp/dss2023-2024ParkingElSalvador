package es.uca.ParkingElSalvador;

import java.math.BigDecimal;

public class Parking {
    private String nombre;
    private final String direccion_postal;
    private final int capacidadTotal;
    private int plazasDisponibles;
    private int plazasOcupadas;
    private Barrera barrera;
    private QRservice qr;
    private Estandar tarifa;
    private CarService vehiculos;
    private EstanciasService libro;
    private BonoService bonos;
    private Cajero caja;

    public Parking(String n, String d, int capT){
        nombre = n;
        direccion_postal = d;
        capacidadTotal = capT;
        plazasDisponibles = capT; 
        plazasOcupadas = 0;
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


    // Getters y setters 
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nuevo_nombre){
        nombre = nuevo_nombre;
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

    public void setDirectorioQR(String d){
        qr.setDirectorio(d);
    }

    public CarService getVehiculos() {
        return vehiculos;
    }

    public EstanciasService getLibro() {
        return libro;
    }

    public Informe crearInforme(){
        return new Informe(this);
    }

    public BonoService getBonos() {
        return bonos;
    }

    public Cajero getCaja() {
        return caja;
    }

    public String toString(){
        return nombre+" con direccion postal "+direccion_postal+" tiene una capacidad de "+capacidadTotal;
    }
    

    public void entrada() throws Exception {
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
            vehiculos.save(vehiculo);
            barrera.cerrarBarrera();
            plazasDisponibles--;
            plazasOcupadas++;

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
            plazasDisponibles++;
            plazasOcupadas--;

        }
    }

    // En caso de no funcionar el lector de qr

    public void entrada(String matricula) throws Exception {
        Vehiculo vehiculo = new Vehiculo(matricula);
        // Comprobamos que haya espacio
        if(plazasDisponibles > 0){
            // Abrimos la barrera en caso de estar cerrada
            if(!barrera.estaAbierta())
                barrera.abrirBarrera();
            // Generamos el ticket y le damos acceso
            qr.generarCodigoQR(matricula);
            vehiculos.save(vehiculo);
            libro.save(vehiculo);
            barrera.cerrarBarrera();
            plazasDisponibles--;
            plazasOcupadas++;

        }
    }

    public void salida(String matricula) throws Exception{
        Vehiculo vehiculo = vehiculos.getVehiculo(matricula);
        if(vehiculo.estancia().haPagado() || (vehiculo.estancia().poseeBono() && vehiculo.estancia().bonoValido())){
            vehiculo.sale();
            barrera.abrirBarrera();
            // Sale del parking
            barrera.cerrarBarrera();
            vehiculos.delete(vehiculo);
            plazasDisponibles++;
            plazasOcupadas--;

        }
    }

    // Operaciones de pago de tarifa estándar y bonos
    public void vehiculoPagaEstandar(BigDecimal entregado, String mat, char F) throws Exception{
        Vehiculo v = vehiculos.getVehiculo(mat);
        vehiculos.delete(v);
        PagoEstandar pEstandar = new PagoEstandar(caja,v,tarifa.precioMinuto());
        pEstandar.pagar(entregado, F);
        vehiculos.save(v);
    }

    public void vehiculoPagaBonoMensual(BigDecimal entregado, int nMeses, String mat, char F) throws Exception{
        Vehiculo v = vehiculos.getVehiculo(mat);
        vehiculos.delete(v);
        PagoBono pBono = new PagoBono(v);
        pBono.comprarBonoMensual(entregado,nMeses,F);
        vehiculos.save(v);
    }

    public void vehiculoPagaBonoTrimestral(BigDecimal entregado, int nTrimestres, String mat, char F) throws Exception{
        Vehiculo v = vehiculos.getVehiculo(mat);
        vehiculos.delete(v);
        PagoBono pBono = new PagoBono(v);
        pBono.comprarBonoTrimestral(entregado,nTrimestres,F);
        vehiculos.save(v);
    }
    
    public void vehiculoPagaBonoAnual(BigDecimal entregado,int nAnnos, String mat, char F) throws Exception{
        Vehiculo v = vehiculos.getVehiculo(mat);
        vehiculos.delete(v);
        PagoBono pBono = new PagoBono(v);
        pBono.comprarBonoAnual(entregado,nAnnos,F);
        vehiculos.save(v);
    }

}
