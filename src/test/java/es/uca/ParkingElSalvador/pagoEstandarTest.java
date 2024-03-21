import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PagoEstandarTest {
    private CarList vehiculos;
    private QRservice qr;
    private Estandar tarifa;
    private PagoEstandar pagoEstandar;

    @Before
    public void setUp() {
        vehiculos = new CarList();
        qr = new QRservice();
        tarifa = new Estandar();
        tarifa.ponerPrecioAlMinuto(10); // Establecemos un precio de 10 por minuto
        pagoEstandar = new PagoEstandar(vehiculos, tarifa);
    }

    @Test
    public void testCantidad() {
        // Ejecución
        long cantidad = pagoEstandar.cantidad(30); // 30 minutos

        // Verificación
        assertEquals(300, cantidad); // 10 * 30 = 300
    }

    @Test
    public void testPagar() {
        // Configuración
        String matricula = "ABC123";
        Vehiculo vehiculo = new Vehiculo(matricula);
        vehiculo.llega();
        vehiculos.meter(vehiculo);

        // Ejecución
        long pago = pagoEstandar.pagar();

        // Verificación
        assertTrue(vehiculo.haPagado());
        assertEquals(cantidad(vehiculo.duracion()), pago); // 10 * 30 = 300
    }
}
