package es.uca.ParkingElSalvador;

public class Parking {
    private final String nombre;
    private final String direccion_postal;
    private final int capacidadTotal;
    private int plazasDisponibles;
    private int plazasOcupadas;
    private Barrera barrera;
    private QRservice qr;
    private Estandar tarifa;
    private CarRepository vehiculos;
    private CarRegister libro;

    public Parking(String n, String d, int capT){
        nombre = n;
        direccion_postal = d;
        capacidadTotal = capT;
        plazasDisponibles = capT; 
        plazasOcupadas = 0;
        barrera = new Barrera();
        qr = new QRservice();
        tarifa = new Estandar(); 
        vehiculos = new CarRepository();
        libro = new CarRegister();

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

    public CarRepository getVehiculos() {
        return vehiculos;
    }

    public CarRegister getLibro() {
        return libro;
    }

    public Informe crearInforme(){
        return new Informe(this);
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
            vehiculos.meter(vehiculo);
            libro.almacenar(vehiculo);
            barrera.cerrarBarrera();
            plazasDisponibles--;
            plazasOcupadas++;

        }
    }

    public void salida() throws Exception{
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
    public void vehiculoPagaEstandar(String mat) throws Exception{
        pagoEstandar pEstandar = new pagoEstandar(vehiculos.obtener(mat),tarifa.precioMinuto());
        pEstandar.pagar();
    }

    public void vehiculoPagaBonoMensual(int nMeses, String mat) throws Exception{
        pagoBono pBono = new pagoBono(vehiculos.obtener(mat));
        pBono.comprarBonoMensual(nMeses);
    }

    public void vehiculoPagaBonoTrimestral(int nTrimestres, String mat) throws Exception{
        pagoBono pBono = new pagoBono(vehiculos.obtener(mat));
        pBono.comprarBonoTrimestral(nTrimestres);
    }
    
    public void vehiculoPagaBonoAnual(int nAnnos, String mat) throws Exception{
        pagoBono pBono = new pagoBono(vehiculos.obtener(mat));
        pBono.comprarBonoAnual(nAnnos);
    }

}
