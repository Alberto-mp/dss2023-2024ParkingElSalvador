import java.time.LocalDateTime;

public class pagoBono{
    private CarList vehiculos;
    private QRservice qr;

    public pagoBono(CarList vehiculos){
        this.vehiculos = vehiculos;
        this.qr = new QRservice();
    }

    public long comprarBonoMensual(int meses) {
        // Persona pasa el qr por el escaner
        String matricula = qr.leerCodigoQR();
        Vehiculo vehiculo = vehiculos.obtener(matricula);
        BonoMensual bono = new BonoMensual(meses);
        long pago = bono.precioBono();
        vehiculo.compraBono();
        vehiculo.setFinBono(LocalDateTime.now().plusMonths(meses));
        return pago;
    }

    public long comprarBonoTrimestral(int trimestres) {
        // Persona pasa el qr por el escaner
        String matricula = qr.leerCodigoQR();
        Vehiculo vehiculo = vehiculos.obtener(matricula);
        BonoTrimestral bono = new BonoTrimestral(trimestres);
        long pago = bono.precioBono();
        vehiculo.compraBono();
        vehiculo.setFinBono(LocalDateTime.now().plusMonths(3*trimestres));
        return pago;
    }

    public long comprarBonoAnual(int annos) {
        // Persona pasa el qr por el escaner
        String matricula = qr.leerCodigoQR();
        Vehiculo vehiculo = vehiculos.obtener(matricula);
        BonoAnual bono = new BonoAnual(annos);
        long pago = bono.precioBono();
        vehiculo.compraBono();
        vehiculo.setFinBono(LocalDateTime.now().plusYears(annos));
        return pago;
    }

}