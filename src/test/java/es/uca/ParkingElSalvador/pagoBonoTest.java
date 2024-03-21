import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PagoBonoTest {
    private CarList vehiculos;
    private QRservice qr;
    private PagoBono pagoBono;

    @Before
    public void setUp() {
        vehiculos = new CarList();
        qr = new QRservice();
        pagoBono = new PagoBono(vehiculos);
    }

    @Test
    public void testComprarBonoMensual() {
        // Configuración
        String matricula = "ABC123";
        Vehiculo vehiculo = new Vehiculo(matricula);
        vehiculos.meter(vehiculo);

        // Ejecución
        long pago = pagoBono.comprarBonoMensual(1);

        // Verificación
        assertTrue(vehiculo.poseeBono());
        assertNotNull(vehiculo.fechaFinBono());
        assertTrue(LocalDateTime.now().plusDays(30).isEqual(vehiculo.fechaFinBono())); 
        assertEquals(LocalDateTime.now().plusDays(30).getMonthValue(), vehiculo.fechaFinBono().getMonthValue()); // Verifica si el bono vence después de un mes
        assertEquals(1*BonoMensual.precioMensual(), pago); // Verifica el costo del bono
    }

    @Test
    public void testComprarBonoTrimestral() {
        // Configuración
        String matricula = "ABC123";
        Vehiculo vehiculo = new Vehiculo(matricula);
        vehiculos.meter(vehiculo);

        // Ejecución
        long pago = pagoBono.comprarBonoTrimestral(1);

        // Verificación
        assertTrue(vehiculo.poseeBono());
        assertNotNull(vehiculo.fechaFinBono());
        assertTrue(LocalDateTime.now().plusMonths(3).isEqual(vehiculo.fechaFinBono())); 
        assertEquals(LocalDateTime.now().plusMonths(3).getMonthValue(), vehiculo.fechaFinBono().getMonthValue()); // Verifica si el bono vence después de un trimestre
        assertEquals(1 * BonoTrimestral.precioTrimestral(), pago); // Verifica el costo del bono
    }

    @Test
    public void testComprarBonoAnual() {
        // Configuración
        String matricula = "ABC123";
        Vehiculo vehiculo = new Vehiculo(matricula);
        vehiculos.meter(vehiculo);

        // Ejecución
        long pago = pagoBono.comprarBonoAnual(1);

        // Verificación
        assertTrue(vehiculo.poseeBono());
        assertNotNull(vehiculo.fechaFinBono());
        assertTrue(LocalDateTime.now().plusYears(1).isEqual(vehiculo.fechaFinBono())); 
        assertEquals(LocalDateTime.now().plusYears(1).getMonthValue(), vehiculo.fechaFinBono().getMonthValue()); // Verifica si el bono vence después de un anno
        assertEquals(1 * BonoAnual.precioAnual(), pago); // Verifica el costo del bono
    }

}
