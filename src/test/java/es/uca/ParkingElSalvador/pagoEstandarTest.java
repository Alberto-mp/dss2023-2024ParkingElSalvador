package es.uca.ParkingElSalvador;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class pagoEstandarTest {
    private Vehiculo vehiculo;
    private pagoEstandar pago;

    @Before
    public void setUp() {
        vehiculo = new Vehiculo("123ABC");
        pago = new pagoEstandar(vehiculo, 0.5); // Precio por minuto
    }

    @Test
    public void testCantidad() {
        assertEquals("La cantidad debe ser 30 si se han usado 60 minutos y el precio por minuto es 0.5", 30, pago.cantidad(60), 0);
    }

    @Test
    public void testPagar() {
        try {
            pago.pagar(); // Pago de tarifa estándar válido
            assertTrue("El vehículo debería haber pagado la tarifa estándar", vehiculo.haPagado());
            assertNotNull("El vehículo debería tener una cantidad pagada", vehiculo.dineroPagado());
        } catch (Exception e) {
            fail("Se lanzó una excepción inesperada: " + e.getMessage());
        }
    }
}
