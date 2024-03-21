public class Parking {
    private final String nombre;
    private final String direccion_postal;
    private final int capacidadTotal;
    private int plazasDisponibles;
    private int plazasOcupadas;
    private Barrera barrera;
    private QRservice qr;
    private Estandar tarifa;
    private BonoMensual bonoM;
    private BonoTrimestral bonoT;
    private BonoAnual bonoA;
    private CarList vehiculos;
    private CarList libro;
    private Informe informe;

    public Parking(String n, String d, int capT){
        nombre = n;
        direccion_postal = d;
        capacidadTotal = capT;
        plazasDisponibles = capT; 
        plazasOcupadas = 0;
        barrera = new Barrera();
        qr = new QRservice();
        tarifa = new Estandar(); 
        bonoM = new BonoMensual();
        bonoT = new BonoTrimestral();
        bonoA = new BonoAnual();
        vehiculos = new CarList();
        libro = new CarList();
        informe = new Informe();
    }

    public void precioEstandar(long min){
        tarifa.ponerPrecioAlMinuto(min);;
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

    public Tarifacion getTarifa() {
        return tarifa;
    }


    public CarList getVehiculos() {
        return vehiculos;
    }

    public CarList getLibro() {
        return libro;
    }

    public Informe getInforme() {
        return informe;
    }

    public void entrada(){

    }

    public void salida(){

    }

}
