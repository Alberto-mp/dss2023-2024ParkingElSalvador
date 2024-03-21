import java.time.LocalDateTime;

public class pagoEstandar{
    private CarList vehiculos;
    private QRservice qr;
    private Estandar tarifa;

    public pagoEstandar(CarList vehiculos, Estandar precios){
        this.vehiculos = vehiculos;
        this.qr = new QRservice();
        this.tarifa = precios;
    }

    public long cantidad(long minutos){
        return minutos*tarifa.precioMinuto();
    }

    public long pagar() {
        // Persona pasa el qr por el escaner
        String matricula = qr.leerCodigoQR();
        Vehiculo vehiculo = vehiculos.obtener(matricula);
        vehiculo.sale();
        long pago = cantidad(vehiculo.duracion());
        vehiculo.pagarEstandar();
        return pago;
    }

}