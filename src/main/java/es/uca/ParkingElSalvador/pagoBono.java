import java.io.IOException;
import java.time.LocalDateTime;

import javax.naming.NameNotFoundException;

public class pagoBono{
    private CarList vehiculos;
    private QRservice qr;

    public pagoBono(CarList vehiculos){
        this.vehiculos = vehiculos;
        this.qr = new QRservice();
    }

    public long comprarBonoMensual(int meses) throws NameNotFoundException, IOException {
        // Persona pasa el qr por el escaner
        String matricula = qr.leerCodigoQR();
        Vehiculo vehiculo = vehiculos.obtener(matricula);
        BonoMensual bono = new BonoMensual(meses);
        long pago = (long)bono.precioBono();
        vehiculo.compraBono();
        vehiculo.setFinBono(LocalDateTime.now().plusMonths(meses));
        return pago;
    }

    public long comprarBonoTrimestral(int trimestres) throws NameNotFoundException, IOException {
        // Persona pasa el qr por el escaner
        String matricula = qr.leerCodigoQR();
        Vehiculo vehiculo = vehiculos.obtener(matricula);
        BonoTrimestral bono = new BonoTrimestral(trimestres);
        long pago = (long)bono.precioBono();
        vehiculo.compraBono();  
        vehiculo.setFinBono(LocalDateTime.now().plusMonths(3*trimestres));
        return pago;
    }

    public long comprarBonoAnual(int annos) throws NameNotFoundException, IOException {
        // Persona pasa el qr por el escaner
        String matricula = qr.leerCodigoQR();
        Vehiculo vehiculo = vehiculos.obtener(matricula);
        BonoAnual bono = new BonoAnual(annos);
        long pago = (long) bono.precioBono();
        vehiculo.compraBono();
        vehiculo.setFinBono(LocalDateTime.now().plusYears(annos));
        return pago;
    }

}